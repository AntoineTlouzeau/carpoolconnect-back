# carpoolconnect-backend

Pour créer la base de données et l'utilisateur présent dans la conf, exécutez ce script :

```sql
CREATE DATABASE carpoolconnect;
CREATE USER 'user'@'localhost' IDENTIFIED BY 'pass';
GRANT ALL PRIVILEGES ON * . * TO 'user'@'localhost';
FLUSH PRIVILEGES;
```