package com.stschools.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.exceptions.ApiException;
import com.cloudinary.utils.ObjectUtils;
import com.stschools.dto.BlogDTO;
import com.stschools.dto.BlogUserLoveDTO;
import com.stschools.dto.QuizDTO;
import com.stschools.entity.Quiz;
import com.stschools.entity.User;
import com.stschools.exception.ApiRequestException;
import com.stschools.import_file.blogs.BlogExcelImporter;
import com.stschools.payload.blog.BlogRequest;
import com.stschools.repository.BlogRepository;
import com.stschools.entity.Blog;
import com.stschools.repository.UserRepository;
import com.stschools.service.BlogService;
import com.stschools.service.UserService;
import com.stschools.util.ModelMapperControl;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    public final Cloudinary cloudinary;
    private final UserRepository userRepository;
    private final BlogExcelImporter blogExcelImporter;

    @Override
    public DataFetcher<Blog> getBlogByQuery() {
        return dataFetchingEnvironment -> {
            Long blogId = Long.parseLong(dataFetchingEnvironment.getArgument("id"));
            return blogRepository.findById(blogId).get();
        };
    }

    @Override
    public DataFetcher<List<Blog>> getAllBlogsByQuery() {
        return dataFetchingEnvironment -> {
            String type = dataFetchingEnvironment.getArgument("type");
            List<Blog> list;

            switch (type) {
                case "true":
                    list = blogRepository.findAllByStatus(true);
                    break;
                case "false":
                    list = blogRepository.findAllByStatus(false);
                    break;
                default:
                    list = blogRepository.findAllByOrderByIdAsc();
            }
            return list;
        };
    }


    @Override
    @Transactional
    public BlogDTO findBlogById(Long blogId) {
        blogRepository.updateView(blogId);

        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new ApiRequestException("Blog is null!", HttpStatus.BAD_REQUEST));
        return ModelMapperControl.map(blog,BlogDTO.class);
    }

    @Override
    public List<BlogDTO> findAllBlogs() {
        return ModelMapperControl.mapAll(blogRepository.findAllByOrderByIdAsc(), BlogDTO.class);
    }

    @Override
    public List<BlogDTO> getAllBlogsByLove(Long id) throws JSONException {
        List<Blog> blogs = blogRepository.findAllByOrderByIdAsc();
        for (Blog blog: blogs) {
            JSONArray userLove = new JSONArray(blog.getUserLove());

            for(int i=0; i < userLove.length(); i++)
            {
                JSONObject object = userLove.getJSONObject(i);
                String idUser = object.getString("id");
                if (Long.parseLong(idUser) == id)  {
                    blog.setIsLove(true);
                }
            }
            blog.setRecordLove(userLove.length());
        }
        return ModelMapperControl.mapAll(blogs, BlogDTO.class);
    }

    @Override
    public Long deleteBlog(Long blogId){
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new ApiRequestException("Blog is null!", HttpStatus.BAD_REQUEST));

        blog.setIsDeleted(true);
        blogRepository.save(blog);
        return blogId;
    }

    @Override
    public BlogDTO update(BlogDTO blog, Long id){
        Blog blogOld = blogRepository.findById(blog.getId())
                .orElseThrow(() -> new ApiRequestException("Blog is null!", HttpStatus.BAD_REQUEST));


        blogOld.setContent(blog.getContent());
        blogOld.setTitle(blog.getTitle());
//
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new ApiRequestException("User is null!", HttpStatus.BAD_REQUEST));
//        blogOld.setUser(user);
//
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
    public DataFetcher<List<Blog>> getAllBlogsByMe() {
        return dataFetchingEnvironment -> {
            String email = dataFetchingEnvironment.getArgument("email");
            List<Blog> list = blogRepository.findAllByUserEmail(email);
            return list;
        };
    }

    @Override
    @Transactional
    public BlogDTO updateBlogStatus(Long blogId) {
        Blog blog = blogRepository.findBlogById(blogId);
        if (blog.getStatus()) {
            blogRepository.updateBlogStatus(blogId, true);
        } else {
            blogRepository.updateBlogStatus(blogId, false);
        }
        blog.setStatus(!blog.getStatus());
        return ModelMapperControl.map(blog, BlogDTO.class);
    }

    @Override
    @Transactional
    public List<BlogUserLoveDTO> updateLove(Long blogId, Long id) throws JSONException {
        Blog blog = blogRepository.findBlogById(blogId);
        List<BlogUserLoveDTO> listLove = new ArrayList<>();
        Boolean status = true;

        JSONArray userLove = new JSONArray(blog.getUserLove());
        for(int i=0; i < userLove.length(); i++)
        {
            JSONObject object = userLove.getJSONObject(i);
            String idUser = object.getString("id");
            if (Long.parseLong(idUser) != id)  {
                listLove.add(new BlogUserLoveDTO(Long.parseLong(idUser)));
            }else{
                status = false;
            }
        }

        if (status){
            listLove.add(new BlogUserLoveDTO(id));
        }

        blog.setUserLove(listLove.toString());

        return listLove;
    }
}
