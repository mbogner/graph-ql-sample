CREATE TABLE posts
(
    id      uuid         not null default uuid_generate_v4()
        constraint pk_posts primary key,
    name    varchar(255) not null
        constraint uc_posts__name unique,
    user_id uuid         not null
        constraint fk_posts__user references users (id)
) WITHOUT OIDS;
