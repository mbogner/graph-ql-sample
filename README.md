# graph-ql-sample

This is a small sample application integrating spring boot with graphql.

It provides graphiql interface under http://localhost:8080/graphiql for easy testing.

## Graphql

### Schema

The graphql schema of the application is split into multiple files so that every entity has its own .graphqls file.
Those are placed in src/main/resources/graphql and picked up by spring automatically.

#### _root.graphqls
```graphql
# The Root Query for the application
type Query {
}

# The Root Mutation for the application
type Mutation {
}
```

#### user.graphqls
```graphql
type User {
    id: ID!
    name: String
    posts: [Post]
}

extend type Query {
    me: User
    allUsers(page: Int = 0, size: Int = 2): [User]
}

extend type Mutation {
    createUser(name: String!) : User!
}
```

#### post.graphqls
```graphql
type Post {
    id: ID!
    name: String
    author: User!
}

extend type Query {
    allPosts(page: Int = 0, size: Int = 2): [Post]
}

extend type Mutation {
    createPost(name: String!, author: ID!) : Post
}
```

### QueryResolver

Every entity has its own query resolver under `dev.mbo.graphqlsample.graphql.query` and field resolvers can be found in
the subpackage `dev.mbo.graphqlsample.graphql.query.fieldresolvers`.

### MutationResolver

For being able to create data there is a MutationResolver per entity in `dev.mbo.graphqlsample.graphql.mutation`.

### Exception Handling

Relies on Spring defaults.

### Sample Queries

There are some sample queries in `src/test/graphql-queries` like

```graphql
{
    allUsers(page: 0, size: 1000) {
        id
        name
        posts {
            id
            name
        }
    }
}
```

or

```graphql
mutation {
    createUser(name: "test") {
        id
        name
    }
}
```

## Security

The project includes Spring Security but opens the endpoint without authentication.

## Database

PostgreSQL is used as database. It's using docker and is defined in `docker-compose.yml`.
