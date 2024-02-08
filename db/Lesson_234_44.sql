-- шаг 1
set session characteristics as transaction isolation level serializable;
begin transaction;
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
    UPDATE players
    SET gold = gold + 4,
        items = items - 2
    WHERE unit_name = 'Tan';

    UPDATE players
    SET gold = gold - 4,
        items = items + 2
    WHERE unit_name = 'Nimm';
    select * from players;
    savepoint transaction_TanToNimm;
-- шаг 2
    insert into players (player_level, gold, items, unit_name)
    VALUES (1, 2, 0, 'Drumm');
    select * from players;
    savepoint Add_newPlayer;
-- шаг 3
    delete from players where unit_name = 'Trix';
    select * from players;
    savepoint delete_player;
-- шаг 4
    rollback to savepoint Add_newPlayer;
    UPDATE players
    SET gold = gold + 2,
        items = items - 2
    WHERE unit_name = 'Trix';

    UPDATE players
    SET gold = gold - 2,
        items = items + 2
    WHERE unit_name = 'Drumm';
    select * from players where unit_name = 'Trix' or unit_name = 'Drumm';
-- шаг 5
    rollback to savepoint delete_player;
    select * from players;
commit;