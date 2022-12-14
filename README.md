# Atividade-Biblioteca-Java-Spring
Atividade avaliativa da matéria de Arquitetura de Software

<h2>Primeiramente Crie o Database e o usuário:</h2>

```sql
CREATE DATABASE Library;

CREATE USER 'user'@'localhost' IDENTIFIED BY 'password'; 
GRANT ALL PRIVILEGES ON Library. * TO 'user'@'localhost'; 
FLUSH PRIVILEGES;
```

<h2>Após primeira execução, execute as seguintes queries:</h2>

```sql
USE Library;

INSERT INTO Library.user (id, password, username) VALUES (1, '$2a$10$E3CY//muUoAC/1VZfntUoeaNujBfexQoeS8NjIM.TsX/0mUVbuaVu', 'admin');
INSERT INTO Library.user (id, password, username) VALUES (2, '$2a$10$j5QaMopRK41V6/QUwvVvcOF8WHfhUEP.qEZ7Fb5hf4P6p6ZCXzB.W', 'user');

INSERT INTO Library.role (id, role_name) VALUES (3, 'ROLE_ADMIN');
INSERT INTO Library.role (id, role_name) VALUES (4, 'ROLE_USER');

INSERT INTO Library.user_role (user_id, role_id) VALUES (1, 3);
INSERT INTO Library.user_role (user_id, role_id) VALUES (2, 4);

INSERT INTO Library.book (id, author, available, genre, location, name, publishing_house, release_year) VALUES (5, 'Robert C. Martin', true, 'Computação', '7B', 'Código Limpo', 'Alta Books', 2011);
INSERT INTO Library.book (id, author, available, genre, location, name, publishing_house, release_year) VALUES (6, 'Aldous Huxley', true, 'Ficção', '13A', 'Admirável Mundo Novo', 'Biblioteca Azul', 2015);

UPDATE Library.hibernate_sequence SET next_val = 7;
```
<h2>Usuários disponíveis:</h2>
<table>
  <tr>
    <th>Username</th>
    <th>Senha</th>
    <th>Privilégios</th>
  </tr>
  <tr>
    <td>admin</td>
    <td>admin</td>
    <td>Admin</td>
  </tr>
  <tr>
    <td>user</td>
    <td>user</td>
    <td>Usuário</td>
  </tr>
</table>
