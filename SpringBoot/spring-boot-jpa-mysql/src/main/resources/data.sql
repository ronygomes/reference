-- Reset table data from previous run
DELETE FROM tasks;
ALTER TABLE tasks AUTO_INCREMENT = 0;

INSERT INTO tasks (task, due_date) VALUES('Task DB 1', current_date());
INSERT INTO tasks (task, due_date) VALUES('Task DB 2', current_date());
INSERT INTO tasks (task, due_date) VALUES('Task DB 3', current_date());