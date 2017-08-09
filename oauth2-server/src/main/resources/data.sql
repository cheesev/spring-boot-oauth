delete from oauth_client_details;
delete from user_roles;
delete from users;

insert into oauth_client_details (client_id, client_secret, resource_ids, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) values ('my_client_id', 'my_client_secret', null, 'read', 'authorization_code,password,client_credentials,implicit,refresh_token', null, 'ROLE_MY_CLIENT', 36000, 2592000, null, null);
insert into oauth_client_details (client_id, client_secret, resource_ids, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) values ('your_client_id', 'your_client_secret', null, 'write', 'authorization_code,implicit', null, 'ROLE_YOUR_CLIENT', 36000, 2592000, null, null);

INSERT INTO users (username, password, enabled) VALUES ('test', '1111', true);
INSERT INTO users (username, password, enabled) VALUES ('maven', '1111', true);

INSERT INTO user_roles (username, role)
VALUES ('test', 'ROLE_USER,ROLE_ADMIN');
--INSERT INTO user_roles (username, role) VALUES ('test', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('maven', 'ROLE_USER');