package com.stschools.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.stschools.dto.BlogDTO;
import com.stschools.dto.BlogUserLoveDTO;
import com.stschools.entity.Blog;
import com.stschools.entity.User;
import com.stschools.exception.ApiRequestException;
import com.stschools.import_file.blogs.BlogExcelImporter;
import com.stschools.payload.blog.BlogRequest;
import com.stschools.repository.BlogRepository;
import com.stschools.repository.UserRepository;
import com.stschools.service.BlogService;
import com.stschools.util.ModelMapperControl;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    public final Cloudinary cloudinary;
    private final UserRepository userRepository;
    private final BlogExcelImporter blogExcelImporter;

    @Override
    @Transactional
    public BlogDTO findBlogById(Long blogId, Long userId) {
        blogRepository.updateView(blogId);

        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new ApiRequestException("Blog is null!", HttpStatus.BAD_REQUEST));
        filterLove(blog, userId);
        return ModelMapperControl.map(blog, BlogDTO.class);
    }

    private void filterLove(Blog blog, Long user) {
        if (blog != null && blog.getUserLove() != null) {
            JSONArray userLove = new JSONArray(blog.getUserLove());

            for (int i = 0; i < userLove.length(); i++) {
                JSONObject object = userLove.getJSONObject(i);
                long idUser = object.getLong("id");
                if (idUser == user) {
                    blog.setLove(true);
                }
            }
            blog.setRecordLove(userLove.length());
        }
    }

    private void filtersLove(List<Blog> blogs, Long userId) {
        for (Blog blog : blogs) {
            filterLove(blog, userId);
        }
    }

    @Override
    public List<BlogDTO> getAllBlogsByLove(Long userId) {
        List<Blog> blogs = blogRepository.findAllByOrderByIdAsc();
        filtersLove(blogs, userId);
        return ModelMapperControl.mapAll(blogs, BlogDTO.class);
    }

    @Override
    public Long deleteBlog(Long blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new ApiRequestException("Blog is null!", HttpStatus.BAD_REQUEST));

        blog.setIsDeleted(true);
        blogRepository.save(blog);
        return blogId;
    }

    @Override
    public BlogDTO update(BlogDTO blog, Long userId) {
        Blog blogOld = blogRepository.findById(blog.getId())
                .orElseThrow(() -> new ApiRequestException("Blog is null!", HttpStatus.BAD_REQUEST));

        blogOld.setContent(blog.getContent());
        blogOld.setTitle(blog.getTitle());

        filterLove(blogOld, userId);
        return ModelMapperControl.map(blogRepository.save(blogOld), BlogDTO.class);
    }

    @Override
    public BlogDTO addBlog(BlogRequest blog, Long idUser) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(blog.getFile().getBytes(),
                ObjectUtils.asMap("resource_type", "auto", "public_id", "st-school/images/" + blog.getFile().getOriginalFilename()));
        Blog blogNew = new Blog();
        blogNew.setImage(uploadResult.get("secure_url").toString());
        blogNew.setTitle(blog.getTitle());
        blogNew.setContent(blog.getContent());
        blogNew.setSummary("New Summary");

        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new ApiRequestException("User is null!", HttpStatus.BAD_REQUEST));
        blogNew.setUser(user);

        blogNew.setStatus(false);
        return ModelMapperControl.map(blogRepository.save(blogNew), BlogDTO.class);
    }

    @Override
    public List<BlogDTO> addBlog(MultipartFile file) throws IOException {
        List<Blog> blogs = blogExcelImporter.parseExcelFile(file.getInputStream());
        return ModelMapperControl.mapAll(blogRepository.saveAll(blogs), BlogDTO.class);
    }

    @Override
    public List<BlogDTO> getAllBlogsByMe(Long userId) {
        List<Blog> blogs
                = blogRepository.findAllByUserId(userId);
        filtersLove(blogs, userId);
        return ModelMapperControl.mapAll(blogs, BlogDTO.class);
    }

    @Override
    @Transactional
    public BlogDTO updateBlogStatus(Long blogId) {
        Blog blog = blogRepository.findBlogById(blogId);
        blogRepository.updateBlogStatus(blogId, blog.getStatus());
        blog.setStatus(!blog.getStatus());
        return ModelMapperControl.map(blog, BlogDTO.class);
    }

    @Override
    @Transactional
    public BlogDTO updateLove(Long blogId, Long id) {
        Blog blog = blogRepository.findBlogById(blogId);
        List<BlogUserLoveDTO> listLove = new ArrayList<>();
        boolean status = true;

        JSONArray userLove = new JSONArray(blog.getUserLove());
        for (int i = 0; i < userLove.length(); i++) {
            JSONObject object = userLove.getJSONObject(i);
            long idUser = object.getLong("id");
            if (idUser != id) {
                listLove.add(new BlogUserLoveDTO(idUser));
            } else {
                status = false;
            }
        }

        if (status) {
            listLove.add(new BlogUserLoveDTO(id));
        }

        blog.setRecordLove(listLove.size());
        blog.setUserLove(listLove.toString());
        blog.setLove(status);

        return ModelMapperControl.map(blogRepository.save(blog), BlogDTO.class);
    }

    @Override
    public List<BlogDTO> getTopNew(Long id) {
        List<Blog> blogs = blogRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Blog::getId, Comparator.comparing(Math::abs)).reversed())
                .limit(10)
                .collect(Collectors.toList());

        return ModelMapperControl.mapAll(blogs, BlogDTO.class);
    }

    @Override
    public List<BlogDTO> getTopView(Long userId) {
        List<Blog> blogs = blogRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Blog::getView, Comparator.comparing(Math::abs)).reversed())
                .limit(10).collect(Collectors.toList());

        filtersLove(blogs, userId);
        return ModelMapperControl.mapAll(blogs, BlogDTO.class);
    }
}
