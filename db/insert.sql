insert into roles(role_type) values('admin');
insert into roles(role_type) values('manager');

insert into rules_type(rule_type) values('add manager');
insert into rules_type(rule_type) values('add admin');
insert into rules_type(rule_type) values('delete manager');
insert into rules_type(rule_type) values('delete admin');
insert into rules_type(rule_type) values('reading item');
insert into rules_type(rule_type) values('editing item');
insert into rules_type(rule_type) values('delete item');

insert into access_rights(role_id, rule_id) values(1, 1);
insert into access_rights(role_id, rule_id) values(1, 2);
insert into access_rights(role_id, rule_id) values(1, 3);
insert into access_rights(role_id, rule_id) values(1, 4);
insert into access_rights(role_id, rule_id) values(2, 5);
insert into access_rights(role_id, rule_id) values(2, 6);
insert into access_rights(role_id, rule_id) values(2, 7);

insert into users(user_name, role_id) values('Менеджер по работе с физ. лицами Екатерина Т.Н.', 2);

insert into states(ustate_type) values('новая');
insert into states(ustate_type) values('в обработке');
insert into states(ustate_type) values('в пути');
insert into states(ustate_type) values('доставлена');

insert into categories(category_type) values('заказчик физическое лицо');
insert into categories(category_type) values('заказчик юридическое лицо');

insert into items(item, user_id, state_id, category_id)
    values('масло, хлеб, колбаса, яйца, яблоки, капуста, морковка', 1, 1, 1);

insert into attachments(attachment, item_id) values('фото масла', 1);
insert into attachments(attachment, item_id) values('фото яблок', 1);

insert into comments_to_items(comment_to_item, item_id) values('масло именно такое как на фото!!!', 1);
insert into comments_to_items(comment_to_item, item_id) values('яблоки только такого размера, пожалуйста', 1);