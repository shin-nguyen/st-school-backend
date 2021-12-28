package com.stschools.service.graphql;

import com.stschools.service.BlogService;
import com.stschools.service.OrderService;
import graphql.GraphQL;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import com.stschools.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.core.io.Resource;
import graphql.schema.GraphQLSchema;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class GraphQLProvider {
    private final UserService userService;
    private final BlogService blogService;
    private final OrderService orderService;

    @Value("classpath:graphql/schemas.graphql")
    private Resource resource;

    @Getter
    private GraphQL graphQL;

    @PostConstruct
    public void loadSchema() throws IOException{
        File fileSchema = resource.getFile();
        TypeDefinitionRegistry typeRegistry =new SchemaParser().parse(fileSchema);

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
