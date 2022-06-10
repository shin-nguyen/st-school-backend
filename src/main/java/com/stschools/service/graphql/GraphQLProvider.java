package com.stschools.service.graphql;

import com.stschools.service.BlogService;
import com.stschools.service.OrderService;
import graphql.GraphQL;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import com.stschools.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import graphql.schema.GraphQLSchema;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class GraphQLProvider {
    private final UserService userService;
    private final BlogService blogService;
    private final OrderService orderService;

//    @Value("classpath:/graphql/schemas.graphql")
//    private Resource resource;

    @Getter
    private GraphQL graphQL;

    @PostConstruct
    public void loadSchema() throws IOException{
        ClassPathResource classPathResource = new ClassPathResource("resources/graphql/schemas.graphql");
        InputStream inputStream = classPathResource.getInputStream();
        File somethingFile = File.createTempFile("schemas", ".graphql");
        try {
            FileUtils.copyInputStreamToFile(inputStream, somethingFile);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }

        TypeDefinitionRegistry typeRegistry =new SchemaParser().parse(somethingFile);

        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry,wiring);
        graphQL = GraphQL.newGraphQL(schema).build();

    }
    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query",typeWiring -> typeWiring
                        .dataFetcher("user",userService.getUserByQuery())
                        .dataFetcher("users",userService.getAllUsersByQuery())
                        .dataFetcher("blog",blogService.getBlogByQuery())
                        .dataFetcher("blogs",blogService.getAllBlogsByQuery())
                        .dataFetcher("blogsOfMe",blogService.getAllBlogsByMe())
                        .dataFetcher("orders",orderService.findAllByCreateDateTop5())

                ).build();
    }
}
