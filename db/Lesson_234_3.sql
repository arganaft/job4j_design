
create
or replace procedure remove_products(i_count integer)
language 'plpgsql'
as $$
    BEGIN
        DELETE FROM products WHERE count < i_count;
    END
$$;

call remove_products(5);

create
or replace function delete_first_entry()
returns void
language 'plpgsql'
as
$$
    begin
        delete from products where id = (select min(id) from products);
    end;
$$;

select delete_first_entry();

