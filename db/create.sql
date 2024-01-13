create table rules_type(
    id serial primary key,
    rule_type text
);

create table roles(
    id serial primary key,
    role_type text
);

create table access_rights(
    rule_id int references rules_type(id),
    role_id int references roles(id)
);

create table users(
    id serial primary key,
    user_name text,
    role_id int references roles(id)
);

create table states(
    id serial primary key,
    state_type text
);

create table categories(
    id serial primary key,
    category_type text
);

create table items(
    id serial primary key,
    item text,
    user_id int references users(id),
    state_id int references states(id),
    category_id int references categories(id)
);

create table attachments(
    id serial primary key,
    attachment text,
    item_id int references items(id)
);

create table comments_to_items(
    id serial primary key,
    comment_to_item text,
    item_id int references items(id)
);

