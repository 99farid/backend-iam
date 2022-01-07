CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE files(
	id varchar(36) DEFAULT uuid_generate_v4(),
	data_file bytea,
	extention varchar(5),
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);

ALTER TABLE files 
	ADD CONSTRAINT file_pk PRIMARY KEY(id),
	ALTER COLUMN data_file SET NOT NULL,
	ALTER COLUMN extention SET NOT NULL,
	ALTER COLUMN ver SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;

CREATE TABLE item_types(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	code varchar(32),
	item_type_name varchar(32),
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);	
	
ALTER TABLE item_types
	ADD CONSTRAINT item_type_pk PRIMARY KEY(id),
	ADD CONSTRAINT item_type_bk UNIQUE (code),
	ALTER COLUMN code SET NOT NULL,
	ALTER COLUMN item_type_name SET NOT NULL,
	ALTER COLUMN ver SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;

CREATE TABLE items(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	id_item_type varchar(36),
	description varchar(32),
	brand varchar(32),
	serial varchar(32),
	price numeric,
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);
	
ALTER TABLE items
	ADD CONSTRAINT item_pk PRIMARY KEY(id),
	ADD CONSTRAINT item_type_fk FOREIGN KEY (id_item_type)
	REFERENCES item_types(id),
	ALTER COLUMN id_item_type SET NOT NULL,
	ALTER COLUMN description SET NOT NULL,
	ALTER COLUMN brand SET NOT NULL,
	ALTER COLUMN price SET NOT NULL,
	ALTER COLUMN ver SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;

CREATE TABLE status_assets(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	code varchar(32),
	status_asset_name varchar(32),
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);

ALTER TABLE status_assets
	ADD CONSTRAINT status_asset_pk PRIMARY KEY (id),
	ADD CONSTRAINT status_asset_bk UNIQUE(code),
	ALTER COLUMN code SET NOT NULL,
	ALTER COLUMN status_asset_name SET NOT NULL,
	ALTER COLUMN ver SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;

CREATE TABLE condition_assets(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	code varchar(32),
	id_status_asset varchar(36),
	condition_asset_name varchar(32),
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);
ALTER TABLE condition_assets
	ADD CONSTRAINT condition_asset_pk PRIMARY KEY (id),
	ADD CONSTRAINT condition_asset_bk UNIQUE (code),
	ADD CONSTRAINT status_asset_fk FOREIGN KEY (id_status_asset)
	REFERENCES status_assets(id),
	ALTER COLUMN code SET NOT NULL,
	ALTER COLUMN id_status_asset SET NOT NULL,
	ALTER COLUMN condition_asset_name SET NOT NULL,
	ALTER COLUMN ver SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;

CREATE TABLE invoices(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	code varchar(32),
	invoice_date date,
	total_price numeric,
	id_invoice_pict varchar(36),
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);

ALTER TABLE invoices
	ADD CONSTRAINT invoice_pk PRIMARY KEY(id),
	ADD CONSTRAINT invoice_bk UNIQUE(code),
	ADD CONSTRAINT invoice_pict_fk FOREIGN KEY(id_invoice_pict)
	REFERENCES files(id),
	ALTER COLUMN code SET NOT NULL,
	ALTER COLUMN invoice_date SET NOT NULL,
	ALTER COLUMN total_price SET NOT NULL,
	ALTER COLUMN ver SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;

CREATE TABLE companies (
		id varchar(36) DEFAULT uuid_generate_v4 (),
		code varchar(32),
		company_name varchar(64),
		ver integer,
		created_by varchar(36),
		created_date timestamp without time zone,
		updated_by varchar(36),
		updated_date timestamp without time zone,
		is_active boolean
);

ALTER TABLE companies
		ADD CONSTRAINT company_pk PRIMARY KEY(id),
		ADD CONSTRAINT company_bk UNIQUE(code),
		ALTER COLUMN company_name SET NOT NULL,
		ALTER COLUMN code SET NOT NULL,
		ALTER COLUMN ver SET NOT NULL,
		ALTER COLUMN created_by SET NOT NULL,
		ALTER COLUMN created_date SET NOT NULL,
		ALTER COLUMN is_active SET NOT NULL;


CREATE TABLE locations(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	code varchar(32),
	id_company varchar(36),
	locations_name varchar(32),
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);

ALTER TABLE locations
	ADD CONSTRAINT locations_pk PRIMARY KEY (id),
	ADD CONSTRAINT locations_bk UNIQUE (code),
	ADD CONSTRAINT company_fk FOREIGN KEY(id_company)
	REFERENCES companies(id),
	ALTER COLUMN locations_name SET NOT NULL,
	ALTER COLUMN ver SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;


CREATE TABLE assets(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	code varchar(32),
	id_item varchar(36),
	id_status_asset varchar(36),
	id_company varchar(36),
	id_invoice varchar(36),
	expired_date date,
	id_display varchar(36),
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);

ALTER TABLE assets
	ADD CONSTRAINT asset_pk PRIMARY KEY(id),
	ADD CONSTRAINT asset_bk UNIQUE(code),
	ADD CONSTRAINT item_fk FOREIGN KEY(id_item)
	REFERENCES items(id),
	ADD CONSTRAINT status_asset_fk FOREIGN KEY(id_status_asset)
	REFERENCES status_assets(id),
	ADD CONSTRAINT company_fk FOREIGN KEY(id_company)
	REFERENCES companies(id),
	ADD CONSTRAINT invoice_fk FOREIGN KEY(id_invoice)
	REFERENCES invoices(id),
	ADD CONSTRAINT display_fk FOREIGN KEY(id_display)
	REFERENCES files(id),
	ALTER COLUMN code SET NOT NULL,
	ALTER COLUMN id_item SET NOT NULL,
	ALTER COLUMN id_status_asset SET NOT NULL,
	ALTER COLUMN id_company SET NOT NULL,
	ALTER COLUMN id_invoice SET NOT NULL,
	ALTER COLUMN ver SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;
	
CREATE TABLE permissions (
	id varchar(36) DEFAULT uuid_generate_v4 (),
	code varchar(32),
	permission_name varchar(64),
	permission_link varchar(64),
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);

ALTER TABLE permissions
	ADD CONSTRAINT permission_pk PRIMARY KEY (id),
	ADD CONSTRAINT code_permission_bk UNIQUE (code),
	ALTER COLUMN code SET NOT NULL,
	ALTER COLUMN permission_name SET NOT NULL,
	ALTER COLUMN permission_link SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;




CREATE TABLE roles (
	id varchar(36) DEFAULT uuid_generate_v4 (),
	code varchar(32),
	role_name varchar(64),
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);

ALTER TABLE roles
	ADD CONSTRAINT role_pk PRIMARY KEY (id),
	ADD CONSTRAINT code_role_bk UNIQUE (code),
	ALTER COLUMN code SET NOT NULL,
	ALTER COLUMN role_name SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;

CREATE TABLE role_permissions (
	id varchar(36) DEFAULT uuid_generate_v4 (),
	id_role varchar(36),
	id_permission varchar(36),
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);

ALTER TABLE role_permissions
	ADD CONSTRAINT role_permission_pk PRIMARY KEY (id),
	ADD CONSTRAINT role_fk FOREIGN KEY (id_role) 
	REFERENCES roles(id),
	ADD CONSTRAINT permission_fk FOREIGN KEY (id_permission)
	REFERENCES permissions(id),
	ALTER COLUMN id_role SET NOT NULL,
	ALTER COLUMN id_permission SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;

CREATE TABLE users (
	id varchar(36) DEFAULT uuid_generate_v4 (),
	id_role varchar(36),
	email varchar(32),
	pass text,
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);

ALTER TABLE users
	ADD CONSTRAINT user_pk PRIMARY KEY (id),
	ADD CONSTRAINT role_fk FOREIGN KEY (id_role) 
REFERENCES roles(id),
	ALTER COLUMN id_role SET NOT NULL,
	ALTER COLUMN email SET NOT NULL,
	ALTER COLUMN pass SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;

CREATE TABLE employees (
	id varchar(36) DEFAULT uuid_generate_v4 (),
	id_company varchar(36),
	nip varchar(32),
	email varchar(32),
	full_name varchar(64),
	phone_no varchar(13),
	department varchar(32),
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);

ALTER TABLE employees
	ADD CONSTRAINT employee_pk PRIMARY KEY (id),
	ADD CONSTRAINT company_fk FOREIGN KEY (id_company) 
	REFERENCES companies(id),
	ALTER COLUMN id_company SET NOT NULL,
	ALTER COLUMN nip SET NOT NULL,
	ALTER COLUMN full_name SET NOT NULL,
	ALTER COLUMN phone_no SET NOT NULL,
	ALTER COLUMN email SET NOT NULL,
	ALTER COLUMN department SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;

CREATE TABLE profile_users (
	id varchar(36) DEFAULT uuid_generate_v4 (),
	id_user varchar(36),
	id_employee varchar(36),
	id_profile_pict varchar(36),
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);

ALTER TABLE profile_users
	ADD CONSTRAINT profile_user_pk PRIMARY KEY (id),
	ADD CONSTRAINT user_fk FOREIGN KEY (id_user) 
	REFERENCES users(id),
	ADD CONSTRAINT employee_fk FOREIGN KEY (id_employee) 
	REFERENCES employees(id),
	ADD CONSTRAINT profile_pict_fk FOREIGN KEY (id_profile_pict) 
	REFERENCES files(id),
	ALTER COLUMN id_user SET NOT NULL,
	ALTER COLUMN id_employee SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;
	
CREATE TABLE transactions_out(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	code varchar(32),
	id_employee varchar(36),
	id_location varchar(36),
	id_general_item varchar(36),
	check_out_date date,
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);

ALTER TABLE transactions_out
	ADD CONSTRAINT transaction_out_pk PRIMARY KEY (id),
	ADD CONSTRAINT code_transaction_out_bk UNIQUE (code),
	ADD CONSTRAINT employee_fk FOREIGN KEY (id_employee) REFERENCES employees(id),
	ADD CONSTRAINT location_fk FOREIGN KEY (id_location) REFERENCES locations(id),
	ADD CONSTRAINT general_item_fk FOREIGN KEY (id_general_item) REFERENCES assets(id),
	ALTER COLUMN code SET NOT NULL,
	ALTER COLUMN check_out_date SET NOT NULL,
	ALTER COLUMN ver SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;


CREATE TABLE detail_transactions_out (
	id varchar(36) DEFAULT uuid_generate_v4 (),
	id_transaction_out varchar(36),
	id_asset varchar(36),
	due_date date,
	status_email boolean,
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);

ALTER TABLE detail_transactions_out
	ADD CONSTRAINT detail_transactiom_out_pk PRIMARY KEY(id),
	ADD CONSTRAINT transaction_out_fk FOREIGN KEY (id_transaction_out) REFERENCES transactions_out(id),
	ADD CONSTRAINT asset_fk FOREIGN KEY (id_asset) REFERENCES assets(id),
	ALTER COLUMN id_transaction_out SET NOT NULL,
	ALTER COLUMN id_asset SET NOT NULL,
	ALTER COLUMN status_email SET NOT NULL,
	ALTER COLUMN due_date SET NOT NULL,
	ALTER COLUMN ver SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;

CREATE TABLE transactions_in(
	id varchar(36) DEFAULT uuid_generate_v4(),
	code varchar(32),
	id_transaction_out varchar(36),
	check_in_date date,
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);

ALTER TABLE transactions_in
	ADD CONSTRAINT transaction_in_pk PRIMARY KEY (id),
	ADD CONSTRAINT transaction_in_bk UNIQUE (code),
	ADD CONSTRAINT transaction_out_fk FOREIGN KEY (id_transaction_out)
	REFERENCES transactions_out(id),
	ALTER COLUMN code SET NOT NULL,
	ALTER COLUMN id_transaction_out SET NOT NULL,
	ALTER COLUMN check_in_date SET NOT NULL,
	ALTER COLUMN ver SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;
	

CREATE TABLE detail_transactions_in(
	id varchar(36) DEFAULT uuid_generate_v4(),
	id_transaction_in varchar(36),
	id_asset varchar(36),
	id_condition_asset varchar(36),
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);

ALTER TABLE detail_transactions_in
	ADD CONSTRAINT detail_transaction_in_pk PRIMARY KEY(id),
	ADD CONSTRAINT transaction_in_fk FOREIGN KEY(id_transaction_in)
	REFERENCES transactions_in(id),
	ADD CONSTRAINT asset_fk FOREIGN KEY(id_asset)
	REFERENCES assets(id),
	ADD CONSTRAINT condition_asset_fk FOREIGN KEY(id_condition_asset)
	REFERENCES condition_assets(id),
	ALTER COLUMN id_transaction_in SET NOT NULL,
	ALTER COLUMN id_asset SET NOT NULL,
	ALTER COLUMN id_condition_asset SET NOT NULL,
	ALTER COLUMN ver SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;

CREATE TABLE track_activity(
	id varchar(36) DEFAULT uuid_generate_v4(),
	code varchar(32),
	name_asset varchar(64),
	status_asset varchar(64),
	activity varchar(64),
	code_transaction varchar(32),
	date_activity timestamp without time zone,
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);

ALTER TABLE track_activity
	ADD CONSTRAINT track_activity_pk PRIMARY KEY(id),
	ADD CONSTRAINT code_track_bk UNIQUE(code),
	ALTER COLUMN code SET NOT NULL,
	ALTER COLUMN name_asset SET NOT NULL,
	ALTER COLUMN status_asset SET NOT NULL,
	ALTER COLUMN activity SET NOT NULL,
	ALTER COLUMN date_activity SET NOT NULL,
	ALTER COLUMN ver SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;
	
CREATE TABLE general_template(
	id varchar(36) DEFAULT uuid_generate_v4(),
	code varchar(36),
	data_template text,
	ver integer,
	created_by varchar(36),
	created_date timestamp without time zone,
	updated_by varchar(36),
	updated_date timestamp without time zone,
	is_active boolean
);

ALTER TABLE general_template 
	ADD CONSTRAINT general_template_pk PRIMARY KEY(id),
	ADD CONSTRAINT general_template_bk unique(code),
	ALTER COLUMN data_template SET NOT NULL,
	ALTER COLUMN ver SET NOT NULL,
	ALTER COLUMN created_by SET NOT NULL,
	ALTER COLUMN created_date SET NOT NULL,
	ALTER COLUMN is_active SET NOT NULL;
	

					

INSERT INTO roles (code, role_name, ver, created_by, created_date, is_active)
			VALUES	('RL1', 'Super Admin', 0, '1', NOW(), TRUE),
					('RL2', 'Non Admin', 0, '1', NOW(), TRUE),
					('RL3', 'Admin', 0, '1', NOW(), TRUE),
					('SYS', 'SYSTEM', 0, '1', NOW(), TRUE);
					
INSERT INTO companies (code, company_name, ver, created_by, created_date, is_active)
			VALUES	('LWN', 'Lawencon', 0, '1', NOW(), TRUE),
					('LNV', 'Linov', 0, '1', NOW(), TRUE);
				

				
INSERT INTO users (id_role, email, pass, ver, created_by, created_date, is_active)
			VALUES	((SELECT id FROM roles WHERE code = 'RL1'), 'john123@gmail.com','12345', 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL2'), 'shani56@gmail.com','12345', 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL3'), 'trianaayu@gmail.com','12345', 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'SYS'), 'SYSTEM','12345', 0, '1', NOW(), TRUE);
				
INSERT INTO permissions (code, permission_name, permission_link, ver, created_by, created_date, is_active)
			VALUES	
			('C-Asset','Create Asset', '/assets/new', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('R-Asset','Read Asset', '/assets', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('C-Company','Create Company', '/companies/new', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('U-Company','Update Company', '/companies/modify', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('R-Company','Read Company', '/companies', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('C-Condition','Create Condition', '/condition-assets/new', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('U-Condition','Update Condition', '/condition-assets/modify', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('R-Condition','Read Condition', '/condition-assets', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('C-Employee','Create Employee', '/employees/new', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('U-Employee','Update Employee', '/employees/modify', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('R-Employee','Read Employee', '/employees', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('C-Invoice','Create Invoice', '/invoices/new', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('U-Invoice','Update Invoice', '/invoices/modify', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('R-Invoice','Read Invoice', '/invoices', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('C-Type','Create Type', '/item-types/new', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('U-Type','Update Type', '/item-types/modify', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('R-Type','Read Type', '/item-types', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('RU-Item','Read and Update Item', '/items-detail', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('C-Location','Create Location', '/locations/new', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('U-Location','Update Location', '/locations/modify', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('R-Location','Read Location', '/locations', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('C-Permission','Create Permission', '/permissions/new', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('U-Permission','Update Permission', '/permissions/modify', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('R-Permission','Read Permission', '/permissions', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('R-Role-Permission','Read Role Permission', '/role-permissions/detail', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('C-Profile','Create Profile User', '/profile-users/new', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('U-Profile','Update Profile User', '/profile-users/modify', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('R-Profile','Read Profile User', '/profile-users', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('C-Role','Create Role', '/roles/new', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('U-Role','Update Role', '/roles/modify', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('R-Role','Read Role', '/roles', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('C-Status','Create Status', '/status-assets/new', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('U-Status','Update Status', '/status-assets/modify', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('R-Status','Read Status', '/status-assets', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('R-Track','Read Track', '/track-activities', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('C-User','Create User', '/users/new', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('U-User','Update User', '/users/modify', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('R-User','Read User', '/users', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			
			('C-Trx-In','Create Transaction In', '/transactions-in/new', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('R-Trx-In','Read Transaction In', '/transactions-in', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			
			('C-Trx-Out','Create Transaction Out', '/transactions-out/new', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('R-Trx-Out','Read Transaction Out', '/transactions-out', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE);
INSERT INTO permissions (code, permission_name, permission_link, ver, created_by, created_date, is_active)
			VALUES		('R-Report-Asset','Read Report Asset', '/assets-expired', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE);

INSERT INTO permissions (code, permission_name, permission_link, ver, created_by, created_date, is_active)
			VALUES		('R-Report-Transactions','Read Report Transactions', '/transactions-out/expired', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE);


INSERT INTO permissions (code, permission_name, permission_link, ver, created_by, created_date, is_active)
			VALUES	
			('D-Company','Delete Company', '-', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('D-Status','Delete Status', '-', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('D-Role','Delete Role', '-', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('D-Location','Delete Asset', '-', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('D-Type','Delete Asset', '-', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('D-Condition','Delete Asset', '-', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('D-Employee','Delete Asset', '-', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE),
			('D-Permission','Delete Asset', '-', 0, (SELECT id FROM users WHERE email = 'SYSTEM'), NOW(), TRUE);

INSERT INTO role_permissions (id_role, id_permission, ver, created_by, created_date, is_active)
			VALUES	((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'C-Asset'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-Asset'), 0, '1', NOW(), TRUE),
					
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'C-Company'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'U-Company'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-Company'), 0, '1', NOW(), TRUE),
					
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'C-Condition'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'U-Condition'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-Condition'), 0, '1', NOW(), TRUE),
					
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'C-Employee'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'U-Employee'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-Employee'), 0, '1', NOW(), TRUE),
					
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'C-Invoice'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'U-Invoice'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-Invoice'), 0, '1', NOW(), TRUE),
					
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'C-Type'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'U-Type'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-Type'), 0, '1', NOW(), TRUE),
					
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'RU-Item'), 0, '1', NOW(), TRUE),
					
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'C-Location'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'U-Location'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-Location'), 0, '1', NOW(), TRUE),
					
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'C-Permission'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'U-Permission'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-Permission'), 0, '1', NOW(), TRUE),
					
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-Role-Permission'), 0, '1', NOW(), TRUE),
					
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'C-Profile'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'U-Profile'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-Profile'), 0, '1', NOW(), TRUE),
					
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'C-Role'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'U-Role'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-Role'), 0, '1', NOW(), TRUE),
			
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'C-Status'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'U-Status'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-Status'), 0, '1', NOW(), TRUE),
					
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-Track'), 0, '1', NOW(), TRUE),
					
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'C-User'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'U-User'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-User'), 0, '1', NOW(), TRUE),
					
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'C-Status'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'U-Status'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-Status'), 0, '1', NOW(), TRUE),
					
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'C-Status'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'U-Status'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-Status'), 0, '1', NOW(), TRUE),
					
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'C-Trx-In'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-Trx-In'), 0, '1', NOW(), TRUE),
					
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'C-Trx-Out'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-Trx-Out'), 0, '1', NOW(), TRUE);
			
INSERT INTO role_permissions (id_role, id_permission, ver, created_by, created_date, is_active)
			VALUES ((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-Report-Asset'), 0, '1', NOW(), TRUE);
		
INSERT INTO role_permissions (id_role, id_permission, ver, created_by, created_date, is_active)
			VALUES ((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'R-Report-Transactions'), 0, '1', NOW(), TRUE);

INSERT INTO role_permissions (id_role, id_permission, ver, created_by, created_date, is_active)
			VALUES	((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'D-Company'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'D-Status'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'D-Role'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'D-Location'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'D-Type'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'D-Condition'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'D-Employee'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'D-Permission'), 0, '1', NOW(), TRUE);
			
INSERT INTO employees (id_company, nip, full_name, phone_no, department, ver, created_by, created_date, is_active, email)
			VALUES	((SELECT id FROM companies WHERE code = 'LWN'), '198609262012051001','Jhonanendra Nugraha', '081229659944', 'Human Resource', 0, '1', NOW(), true, 'john123@gmail.com'),
					((SELECT id FROM companies WHERE code = 'LWN'), '198409192014051002','Shani Rachma', '081329659984', 'Human Resource', 0, '1', NOW(), true, 'shani56@gmail.com'),
					((SELECT id FROM companies WHERE code = 'LWN'), '198709182015051003','Triana Ayu', '081249659974', 'Accounting', 0, '1', NOW(), true, 'trianaayu@gmail.com');
					
INSERT INTO profile_users (id_user, id_employee, ver, created_by, created_date, is_active)
			VALUES	((SELECT id FROM users WHERE  email = 'john123@gmail.com'), (SELECT id FROM employees WHERE nip = '198609262012051001'), 0, '1', now(), TRUE),
					((SELECT id FROM users WHERE  email = 'shani56@gmail.com'), (SELECT id FROM employees WHERE nip = '198409192014051002'), 0, '1', now(), TRUE),
					((SELECT id FROM users WHERE  email = 'trianaayu@gmail.com'), (SELECT id FROM employees WHERE nip = '198709182015051003'), 0, '1', now(), TRUE);

INSERT INTO item_types (code, item_type_name, ver, created_by, created_date, is_active) 
			VALUES	('GNL', 'General', 0, '1', now(), true),
					('LCS', 'Licences', 0, '1', now(), true),
					('CPN', 'Component', 0, '1', now(), true),
					('CSM', 'Consumable', 0, '1', now(), true);
	
	
INSERT INTO status_assets (code, status_asset_name, ver, created_by, created_date, is_active) 
			VALUES	('SA1', 'Deployable', 0, '1', now(), true),
					('SA2', 'Undeployable', 0, '1', now(), true),
					('SA3', 'On Assign', 0, '1', now(), true),
					('SA4', 'Archive', 0, '1', now(), true),
					('SA5', 'Pending', 0, '1', now(), true);
	
	
INSERT INTO condition_assets(code, id_status_asset, condition_asset_name, ver, created_by, created_date, is_active)
					VALUES	('CA1', (SELECT id FROM status_assets WHERE code = 'SA1'),'Ready to Deploy', 0, '1', now(), true),
							('CA2', (SELECT id FROM status_assets WHERE code = 'SA2'),'Broken', 0, '1', now(), true),
							('CA3', (SELECT id FROM status_assets WHERE code = 'SA2'),'On Repair', 0, '1', now(), true),
							('CA4', (SELECT id FROM status_assets WHERE code = 'SA4'),'Lost/Stolen', 0, '1', now(), true);
	
	
INSERT INTO items (id_item_type, description, brand, serial, price, ver, created_by, created_date, is_active) 
			VALUES	((SELECT id FROM item_types WHERE code = 'GNL'), 'Laptop1', 'ASUS', 'A442U', 1200000, 0, '1', now(), true),
					((SELECT id FROM item_types WHERE code = 'GNL'), 'Laptop2', 'ASUS', 'A442U', 1200000, 0, '1', now(), true),
					((SELECT id FROM item_types WHERE code = 'GNL'), 'Laptop3', 'ASUS', 'A442U', 1200000, 0, '1', now(), true),
					((SELECT id FROM item_types WHERE code = 'CPN'), 'Hard Disk 1TB', 'Sandisk', 'H2421', 120000, 0, '1', now(), true),
					((SELECT id FROM item_types WHERE code = 'CPN'), 'Hard Disk 2TB', 'Sandisk', 'H2421', 120000, 0, '1', now(), true),
					((SELECT id FROM item_types WHERE code = 'CPN'), 'Hard Disk 3TB', 'Sandisk', 'H2421', 120000, 0, '1', now(), true),
					((SELECT id FROM item_types WHERE code = 'CSM'), 'Spidol', 'Snowman', 'BLCK11', 120000, 0, '1', now(), true),
					((SELECT id FROM item_types WHERE code = 'GNL'), 'Kursi Mekanik', 'Olimpik', '88WSA', 1200000, 0, '1', now(), true);
	
	
INSERT INTO invoices (code, invoice_date, total_price, ver, created_by, created_date, is_active) 
			VALUES	('INVC1', now(), 100000000, 0, '1', now(), true),
					('INVC2', now(), 200000000, 0, '1', now(), true),
					('INVC3', now(), 300000000, 0, '1', now(), true),
					('INVC4', now(), 400000000, 0, '1', now(), true),
					('INVC5', now(), 100000000, 0, '1', now(), true),
					('INVC6', now(), 100000000, 0, '1', now(), true);
	
INSERT INTO locations (code, id_company, locations_name, ver, created_by, created_date, is_active) 
			VALUES	('RM1', (SELECT id FROM companies WHERE code = 'LWN'), 'Ruang Tengah', 0, '1', now(), true),
					('RM2', (SELECT id FROM companies WHERE code = 'LWN'), 'Ruang Bootcamp', 0, '1', now(), true),
					('RM3', (SELECT id FROM companies WHERE code = 'LWN'), 'Ruang Pantry', 0, '1', now(), true),
					('RM4', (SELECT id FROM companies WHERE code = 'LWN'), 'Ruang Meeting', 0, '1', now(), true);

INSERT INTO assets (code, id_item, id_status_asset, id_company, id_invoice, ver, created_by, created_date, is_active) 
			VALUES	('LPT1', (SELECT id FROM items WHERE description = 'Laptop1'), (SELECT id FROM status_assets WHERE code = 'SA1'), 
					(SELECT id FROM companies WHERE code = 'LWN'), (SELECT id FROM invoices WHERE code = 'INVC1'), 0, '1', now(), true),
					('HDD1', (SELECT id FROM items WHERE description = 'Hard Disk 1TB'), (SELECT id FROM status_assets WHERE code = 'SA1'), 
					(SELECT id FROM companies WHERE code = 'LWN'), (SELECT id FROM invoices WHERE code = 'INVC1'), 0, '1', now(), true);

INSERT INTO items (id_item_type, description, brand, serial, price, ver, created_by, created_date, is_active) 
			VALUES	((SELECT id FROM item_types WHERE code = 'LCS'), 'Windows', 'Microsoft', 'Pro', 1200000, 0, '1', now(), true);
				
INSERT INTO assets (code, id_item, id_status_asset, id_company, id_invoice, ver, created_by, created_date, is_active) 
			VALUES	('LSC1', (SELECT id FROM items WHERE description = 'Windows'), (SELECT id FROM status_assets WHERE code = 'SA1'), 
					(SELECT id FROM companies WHERE code = 'LWN'), (SELECT id FROM invoices WHERE code = 'INVC1'), 0, '1', now(), true);
				
			UPDATE assets SET expired_date = '2021-12-12' WHERE code = 'LSC1';
	
INSERT INTO transactions_out (code, id_employee, check_out_date, ver, created_by, created_date, is_active)
			VALUES	('TRXO1', (SELECT id FROM employees WHERE nip = '198609262012051001'), '2021-12-20', 0, '1', NOW(), TRUE);

INSERT INTO transactions_out (code, id_employee, check_out_date, ver, created_by, created_date, is_active)
			VALUES	('TRXO5', (SELECT id FROM employees WHERE nip = '198709182015051003'), '2022-12-24', 0, '1', NOW(), TRUE);

INSERT INTO transactions_out (code, id_location, check_out_date, ver, created_by, created_date, is_active)
			VALUES	('TRXO2', (SELECT id FROM locations WHERE code = 'RM1'), '2021-12-20', 0, '1', NOW(), TRUE);
				
INSERT INTO transactions_out (code, id_general_item, check_out_date, ver, created_by, created_date, is_active)
					VALUES	('TRXO3', (SELECT id FROM assets WHERE code = 'LPT1'), '2021-12-20', 0, '1', NOW(), TRUE);

INSERT INTO detail_transactions_out (id_transaction_out, id_asset, due_date, ver, created_by, created_date, is_active,status_email)
			VALUES	((SELECT id FROM transactions_out WHERE code = 'TRXO1'), (SELECT id FROM assets WHERE code = 'LPT1'), '2021-12-20', 0, '1', NOW(), true, false);

INSERT INTO transactions_in (code, id_transaction_out, check_in_date, ver, created_by, created_date, is_active)
			VALUES	('TRI1', (SELECT id FROM transactions_out WHERE code = 'TRXO1'), '2022-10-20', 0, '1', NOW(), TRUE),
					('TRI2', (SELECT id FROM transactions_out WHERE code = 'TRXO2'), '2022-11-20', 0, '1', NOW(), TRUE),
					('TRI3', (SELECT id FROM transactions_out WHERE code = 'TRXO3'), '2022-12-20', 0, '1', NOW(), TRUE);


INSERT INTO general_template (code, data_template, ver, created_by, created_date, is_active) VALUES
	('SEND_REPORTS', '<!DOCTYPE html>
<!-- saved from url=(0064)https://cdn.getvero.com/dd-editor/templates/bangkok/bangkok.html -->
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml"
    xmlns:o="urn:schemas-microsoft-com:office:office">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
    </title>
    <!--[if !mso]><!-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--<![endif]-->

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style type="text/css">
        #outlook a {
            padding: 0;
        }

        body {
            margin: 0;
            padding: 0;
            -webkit-text-size-adjust: 100%;
            -ms-text-size-adjust: 100%;
        }

        table,
        td {
            border-collapse: collapse;
            mso-table-lspace: 0pt;
            mso-table-rspace: 0pt;
        }

        img {
            border: 0;
            height: auto;
            line-height: 100%;
            outline: none;
            text-decoration: none;
            -ms-interpolation-mode: bicubic;
        }

        p {
            display: block;
            margin: 13px 0;
        }
    </style>
    <!--[if mso]> 
                      <xml> 
                      <o:OfficeDocumentSettings> 
                        <o:AllowPNG/> 
                      <o:PixelsPerInch>96</o:PixelsPerInch> 
                  </o:OfficeDocumentSettings> 
                  </xml> 
                    <![endif]-->
    <!--[if lte mso 11]> 
                      <style type="text/css"> 
                        .mj-outlook-group-fix { width:100% !important; } 
                  </style> 
                    <![endif]-->
    <!--[if !mso]><!-->
    <style type="text/css">
        @import url(https://fonts.googleapis.com/css?family=Nunito:normal,italic,bold&display=swap);
    </style>
    <!--<![endif]-->
    <style type="text/css">
        @media only screen and (min-width:480px) {
            .mj-column-per-100 {
                width: 100% !important;
                max-width: 100%;
            }

            .mj-column-per-33-333333333333336 {
                width: 33.333333333333336% !important;
                max-width: 33.333333333333336%;
            }
        }
    </style>
    <style media="screen and (min-width:480px)">
        .moz-text-html .mj-column-per-100 {
            width: 100% !important;
            max-width: 100%;
        }

        .moz-text-html .mj-column-per-33-333333333333336 {
            width: 33.333333333333336% !important;
            max-width: 33.333333333333336%;
        }
    </style>
    <style type="text/css">
        @media only screen and (max-width:480px) {
            table.mj-full-width-mobile {
                width: 100% !important;
            }

            td.mj-full-width-mobile {
                width: auto !important;
            }
        }
    </style>
</head>

<body style="word-spacing:normal;background-color:#EEEDEA;">
    <div style="background-color:#EEEDEA;">
        <!--[if mso | IE]><table align="center" border="0" cellpadding="0" cellspacing="0" class="" style="width:600px;" width="600" ><tr><td style="line-height:0px;font-size:0px;mso-line-height-rule:exactly;"><![endif]-->
        <div style="background:#ffffff;background-color:#ffffff;margin:0px auto;max-width:600px;">
            <table align="center" border="0" cellpadding="0" cellspacing="0" role="presentation"
                style="background:#ffffff;background-color:#ffffff;width:100%;">
                <tbody>
                    <tr>
                        <td
                            style="border-bottom:none;border-left:none;border-right:none;border-top:10px solid #7897cc;direction:ltr;font-size:0px;padding:40px;padding-bottom:0px;padding-left:40px;padding-right:40px;padding-top:20px;text-align:center;">
                            <!--[if mso | IE]><table role="presentation" border="0" cellpadding="0" cellspacing="0"><tr><td class="" style="vertical-align:top;width:520px;" ><![endif]-->
                            <div class="mj-column-per-100 mj-outlook-group-fix"
                                style="font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;">
                                <table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%">
                                    <tbody>
                                        <tr>
                                            <td style="vertical-align:top;padding:0;">
                                                <table border="0" cellpadding="0" cellspacing="0" role="presentation"
                                                    style="" width="100%">
                                                    <tbody>
                                                        <tr>
                                                            <td align="center"
                                                                style="font-size:0px;padding:10px;word-break:break-word;">
                                                                <img height="auto" src="https://i.imgur.com/5FPDJmC.png"
                                                                    style="margin-bottom: 10px; border:0;display:block;outline:none;text-decoration:none;height:auto;width:25%;font-size:13px;"
                                                                    width="75px">
                                                                <div
                                                                    style="font-family:Nunito, Helvetica, Arial, sans-serif;font-size:20px;line-height:1;text-align:center;color:#7897cc;">
                                                                    I - Assets Management</div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="font-size:0px;word-break:break-word;">
                                                                <div style="height:5px;line-height:5px;"> </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="center"
                                                                style="font-size:0px;padding:10px 0;padding-top:10px;padding-right:0;padding-bottom:10px;padding-left:0;word-break:break-word;">
                                                                <p
                                                                    style="border-top:solid 2px #7897cc;font-size:1px;margin:0px auto;width:100%;">
                                                                </p>
                                                                <!--[if mso | IE]><table align="center" border="0" cellpadding="0" cellspacing="0" style="border-top:solid 2px #7897cc;font-size:1px;margin:0px auto;width:520px;" role="presentation" width="520px" ><tr><td style="height:0;line-height:0;"> &nbsp; 
              
            </td></tr></table><![endif]-->
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="font-size:0px;word-break:break-word;">
                                                                <div style="height:30px;line-height:30px;"> </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="center"
                                                                style="font-size:0px;padding:10px;word-break:break-word;">
                                                                <div
                                                                    style="font-family:Nunito, Helvetica, Arial, sans-serif;font-size:40px;line-height:1.25;text-align:center;color:#231F20;">
                                                                    <strong>Notification Report</strong>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <!--[if mso | IE]></td></tr></table><![endif]-->
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <!--[if mso | IE]></td></tr></table><table align="center" border="0" cellpadding="0" cellspacing="0" class="" style="width:600px;" width="600" ><tr><td style="line-height:0px;font-size:0px;mso-line-height-rule:exactly;"><![endif]-->
        <div
            style="background:#ffffff;background-color:#ffffff;margin:0px auto;border-radius:0px 0px 10px 10px;max-width:600px;">
            <table align="center" border="0" cellpadding="0" cellspacing="0" role="presentation"
                style="background:#ffffff;background-color:#ffffff;width:100%;border-radius:0px 0px 10px 10px;">
                <tbody>
                    <tr>
                        <td
                            style="direction:ltr;font-size:0px;padding:40px;padding-bottom:0px;padding-left:50px;padding-right:50px;padding-top:0px;text-align:center;">
                            <!--[if mso | IE]><table role="presentation" border="0" cellpadding="0" cellspacing="0"><tr><td class="" style="vertical-align:top;width:500px;" ><![endif]-->
                            <div class="mj-column-per-100 mj-outlook-group-fix"
                                style="font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;">
                                <table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%">
                                    <tbody>
                                        <tr>
                                            <td style="vertical-align:top;padding:0;">
                                                <table border="0" cellpadding="0" cellspacing="0" role="presentation"
                                                    style="" width="100%">
                                                    <tbody>
                                                        <tr>
                                                            <td align="justify"
                                                                style="font-size:0px;padding:10px;word-break:break-word;">
                                                                <div
                                                                    style="font-family:Nunito, Helvetica, Arial, sans-serif;font-size:24px;line-height:1.5;text-align:justify;color:#231F20;">
                                                                    <span style="word-spacing: normal;">
                                                                       <br> Dear, <br>
                                                                        @user@ <br><br>
                                                                        This is report notification to inform you about
                                                                        report @filename@. Please kindlly check. You can see details by downloading the report attachment below.<br><br>
                                                                        This email send by our system. Do not reply this message.
                                                                        <br>
                                                                        <br>
                                                                        <br>
                                                                        Regards,
                                                                        <br>
                                                                        <br>
                                                                        <br>
                                                                        IAM System</span>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="font-size:0px;word-break:break-word;">
                                                                <div style="height:20px;line-height:20px;"> </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="font-size:0px;word-break:break-word;">
                                                                <div style="height:10px;line-height:10px;"> </div>
                                                            </td>
                                                        </tr>

                                                        <tr>
                                                            <td style="font-size:0px;word-break:break-word;">
                                                                <div style="height:10px;line-height:10px;"> </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="center"
                                                                style="font-size:0px;padding:0px;word-break:break-word;">
                                                                <table border="0" cellpadding="0" cellspacing="0"
                                                                    role="presentation"
                                                                    style="border-collapse:collapse;border-spacing:0px;"
                                                                    class="mj-full-width-mobile">
                                                                    <tbody>
                                                                        <tr>
                                                                            <td style="width:500px;"
                                                                                class="mj-full-width-mobile">
                                                                                <img height="auto"
                                                                                    src="https://i.imgur.com/uwAgW4e.png"
                                                                                    style="border:0;display:block;outline:none;text-decoration:none;height:auto;width:100%;font-size:13px;"
                                                                                    width="500">
                                                                            </td>
                                                                        </tr>
                                                                    </tbody>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <!--[if mso | IE]></td></tr></table><![endif]-->
                        </td>
                    </tr>
                </tbody>
            </table>



</body>

</html>', 0, '1', now(), true);
INSERT INTO general_template (code, data_template, ver, created_by, created_date, is_active) VALUES
	('SEND_PASSWORD', '<!DOCTYPE html> 
        		<!-- saved from url=(0064)https://cdn.getvero.com/dd-editor/templates/bangkok/bangkok.html --> 
            <html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
                  <title> 
                  </title> 
                <!--[if !mso]><!--> 
                  <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
                <!--<![endif]--> 
                 
                <meta name="viewport" content="width=device-width, initial-scale=1"> 
                <style type="text/css"> 
                  #outlook a { 
                      padding: 0; 
                } 
             
                  body { 
                      margin: 0; 
                    padding: 0; 
                    -webkit-text-size-adjust: 100%; 
                    -ms-text-size-adjust: 100%; 
                } 
             
                  table, 
                  td { 
                      border-collapse: collapse; 
                    mso-table-lspace: 0pt; 
                    mso-table-rspace: 0pt; 
                } 
             
                  img { 
                      border: 0; 
                    height: auto; 
                    line-height: 100%; 
                    outline: none; 
                    text-decoration: none; 
                    -ms-interpolation-mode: bicubic; 
                } 
             
                  p { 
                      display: block; 
                    margin: 13px 0; 
                } 
                </style> 
                <!--[if mso]> 
                      <xml> 
                      <o:OfficeDocumentSettings> 
                        <o:AllowPNG/> 
                      <o:PixelsPerInch>96</o:PixelsPerInch> 
                  </o:OfficeDocumentSettings> 
                  </xml> 
                    <![endif]--> 
                <!--[if lte mso 11]> 
                      <style type="text/css"> 
                        .mj-outlook-group-fix { width:100% !important; } 
                  </style> 
                    <![endif]--> 
                <!--[if !mso]><!--> 
                  <style type="text/css"> 
                  @import url(https://fonts.googleapis.com/css?family=Nunito:normal,italic,bold&display=swap); 
                </style> 
                <!--<![endif]--> 
                <style type="text/css"> 
                  @media only screen and (min-width:480px) { 
                      .mj-column-per-100 { 
                        width: 100% !important; 
                      max-width: 100%; 
                  } 
             
                    .mj-column-per-33-333333333333336 { 
                        width: 33.333333333333336% !important; 
                      max-width: 33.333333333333336%; 
                  } 
                } 
                </style> 
                <style media="screen and (min-width:480px)"> 
                  .moz-text-html .mj-column-per-100 { 
                      width: 100% !important; 
                    max-width: 100%; 
                } 
             
                  .moz-text-html .mj-column-per-33-333333333333336 { 
                      width: 33.333333333333336% !important; 
                    max-width: 33.333333333333336%; 
                } 
                </style> 
                <style type="text/css"> 
                  @media only screen and (max-width:480px) { 
                      table.mj-full-width-mobile { 
                        width: 100% !important; 
                  } 
             
                    td.mj-full-width-mobile { 
                        width: auto !important; 
                  } 
                } 
                </style> 
              </head> 
             
              <body style="word-spacing:normal;background-color:#EEEDEA;"> 
                  <div style ="background-color:#EEEDEA;"> 
                    <!--[if mso | IE]><table align="center" border="0" cellpadding="0" cellspacing="0" class="" style="width:600px;" width="600" ><tr><td style="line-height:0px;font-size:0px;mso-line-height-rule:exactly;"><![endif]--> 
                    <div style="background:#ffffff;background-color:#ffffff;margin:0px auto;max-width:600px;"> 
                      <table align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="background:#ffffff;background-color:#ffffff;width:100%;"> 
                        <tbody> 
                          <tr> 
                            <td style="border-bottom:none;border-left:none;border-right:none;border-top:10px solid #7897cc;direction:ltr;font-size:0px;padding:40px;padding-bottom:0px;padding-left:40px;padding-right:40px;padding-top:20px;text-align:center;"> 
                              <!--[if mso | IE]><table role="presentation" border="0" cellpadding="0" cellspacing="0"><tr><td class="" style="vertical-align:top;width:520px;" ><![endif]--> 
                              <div class="mj-column-per-100 mj-outlook-group-fix" style="font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;"> 
                                <table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%"> 
                                  <tbody> 
                                    <tr> 
                                      <td style="vertical-align:top;padding:0;"> 
                                        <table border="0" cellpadding="0" cellspacing="0" role="presentation" style="" width="100%"> 
                                          <tbody> 
                                            <tr> 
                                              <td align="center" style="font-size:0px;padding:10px;word-break:break-word;"> 
											 	<img height="auto" src="https://i.imgur.com/5FPDJmC.png" style="margin-bottom: 10px; border:0;display:block;outline:none;text-decoration:none;height:auto;width:25%;font-size:13px;" width="75px">
                                                <div style="font-family:Nunito, Helvetica, Arial, sans-serif;font-size:20px;line-height:1;text-align:center;color:#7897cc;">I - Assets Management</div> 
                                            </td> 
                                        </tr> 
                                          <tr> 
                                              <td style="font-size:0px;word-break:break-word;"> 
                                                <div style="height:5px;line-height:5px;"> </div> 
                                            </td> 
                                        </tr> 
                                          <tr> 
                                              <td align="center" style="font-size:0px;padding:10px 0;padding-top:10px;padding-right:0;padding-bottom:10px;padding-left:0;word-break:break-word;"> 
                                                <p style="border-top:solid 2px #7897cc;font-size:1px;margin:0px auto;width:100%;"> 
                                                </p> 
                                              <!--[if mso | IE]><table align="center" border="0" cellpadding="0" cellspacing="0" style="border-top:solid 2px #7897cc;font-size:1px;margin:0px auto;width:520px;" role="presentation" width="520px" ><tr><td style="height:0;line-height:0;"> &nbsp; 
              
            </td></tr></table><![endif]--> 
                                          </td> 
                                        </tr> 
                                          <tr> 
                                              <td style="font-size:0px;word-break:break-word;"> 
                                                <div style="height:30px;line-height:30px;"> </div> 
                                            </td> 
                                        </tr> 
                                          <tr> 
                                              <td align="center" style="font-size:0px;padding:10px;word-break:break-word;"> 
                                                <div style="font-family:Nunito, Helvetica, Arial, sans-serif;font-size:48px;line-height:1.25;text-align:center;color:#231F20;"><strong>Here is your new password!</strong></div> 
                                            </td> 
                                        </tr> 
                                      </tbody> 
                                    </table> 
                                  </td> 
                                </tr> 
                              </tbody> 
                            </table> 
                          </div> 
                            <!--[if mso | IE]></td></tr></table><![endif]--> 
                        </td> 
                      </tr> 
                    </tbody> 
                  </table> 
                </div> 
                  <!--[if mso | IE]></td></tr></table><table align="center" border="0" cellpadding="0" cellspacing="0" class="" style="width:600px;" width="600" ><tr><td style="line-height:0px;font-size:0px;mso-line-height-rule:exactly;"><![endif]--> 
                    <div style="background:#ffffff;background-color:#ffffff;margin:0px auto;border-radius:0px 0px 10px 10px;max-width:600px;"> 
                      <table align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="background:#ffffff;background-color:#ffffff;width:100%;border-radius:0px 0px 10px 10px;"> 
                        <tbody> 
                          <tr> 
                            <td style="direction:ltr;font-size:0px;padding:40px;padding-bottom:0px;padding-left:50px;padding-right:50px;padding-top:0px;text-align:center;"> 
                              <!--[if mso | IE]><table role="presentation" border="0" cellpadding="0" cellspacing="0"><tr><td class="" style="vertical-align:top;width:500px;" ><![endif]--> 
                              <div class="mj-column-per-100 mj-outlook-group-fix" style="font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;"> 
                                <table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%"> 
                                  <tbody> 
                                    <tr> 
                                      <td style="vertical-align:top;padding:0;"> 
                                        <table border="0" cellpadding="0" cellspacing="0" role="presentation" style="" width="100%"> 
                                          <tbody> 
                                            <tr> 
                                              <td align="center" style="font-size:0px;padding:10px;word-break:break-word;"> 
                                                <div style="font-family:Nunito, Helvetica, Arial, sans-serif;font-size:24px;line-height:1.5;text-align:center;color:#231F20;"><span style="word-spacing: normal;">You can use this password for login our system and update password leter. Please keep this password carefully and keep it secret</span><br></div> 
                                            </td> 
                                        </tr> 
                                          <tr> 
                                              <td style="font-size:0px;word-break:break-word;"> 
                                                <div style="height:50px;line-height:50px;"> </div> 
                                            </td> 
                                        </tr> 
                                          <tr> 
                                              <td align="center" vertical-align="middle" style="font-size:0px;padding:0px;word-break:break-word;"> 
                                                <table border="0" cellpadding="0" cellspacing="0" role="presentation" style="border-collapse:separate;width:100%;line-height:100%;"> 
                                                  <tbody><tr> 
                                                    <td align="center" bgcolor="#7897cc" role="presentation" style="border:none;border-radius:10px;cursor:auto;mso-padding-alt:10px 25px;background:#7897cc;" valign="middle"> 
                                                      <p style="display:inline-block;background:#7897cc;color:#ffffff;font-family:Nunito, Helvetica, Arial, sans-serif;font-size:20px;font-weight:normal;line-height:1.75;margin:0;text-decoration:none;text-transform:none;padding:10px 25px;mso-padding-alt:0px;border-radius:10px;"> 
                                                        <strong> @password@ </strong><br> 
                                                  </p> 
                                                </td> 
                                              </tr> 
                                            </tbody></table> 
                                          </td> 
                                        </tr> 
                                          <tr> 
                                              <td style="font-size:0px;word-break:break-word;"> 
                                                <div style="height:10px;line-height:10px;"> </div> 
                                            </td> 
                                        </tr> 
                                           
                                          <tr> 
                                              <td style="font-size:0px;word-break:break-word;"> 
                                                <div style="height:30px;line-height:30px;"> </div> 
                                            </td> 
                                        </tr> 
                                          <tr> 
                                              <td align="center" style="font-size:0px;padding:0px;word-break:break-word;"> 
                                                <table border="0" cellpadding="0" cellspacing="0" role="presentation" style="border-collapse:collapse;border-spacing:0px;" class="mj-full-width-mobile"> 
                                                  <tbody> 
                                                    <tr> 
                                                      <td style="width:500px;" class="mj-full-width-mobile"> 
                                                        <img height="auto" src="http://i.imgur.com/S9bLrke.png" style="border:0;display:block;outline:none;text-decoration:none;height:auto;width:100%;font-size:13px;" width="500">
                                                    </td> 
                                                </tr> 
                                              </tbody> 
                                            </table> 
                                          </td> 
                                        </tr> 
                                      </tbody> 
                                    </table> 
                                  </td> 
                                </tr> 
                              </tbody> 
                            </table> 
                          </div> 
                            <!--[if mso | IE]></td></tr></table><![endif]--> 
                        </td> 
                      </tr> 
                    </tbody> 
                  </table> 
                   
               
             
          </body></html>', 0, '1', now(), true);
         
INSERT INTO general_template (code, data_template, ver, created_by, created_date, is_active) VALUES
	('DUE_DATE','<!DOCTYPE html>
<!-- saved from url=(0064)https://cdn.getvero.com/dd-editor/templates/bangkok/bangkok.html -->
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml"
  xmlns:o="urn:schemas-microsoft-com:office:office">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>
  </title>
  <!--[if !mso]><!-->
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!--<![endif]-->

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <style type="text/css">
    #outlook a {
      padding: 0;
    }

    body {
      margin: 0;
      padding: 0;
      -webkit-text-size-adjust: 100%;
      -ms-text-size-adjust: 100%;
    }

    table,
    td {
      border-collapse: collapse;
      mso-table-lspace: 0pt;
      mso-table-rspace: 0pt;
    }

    img {
      border: 0;
      height: auto;
      line-height: 100%;
      outline: none;
      text-decoration: none;
      -ms-interpolation-mode: bicubic;
    }

    p {
      display: block;
      margin: 13px 0;
    }
  </style>
  <!--[if mso]> 
                      <xml> 
                      <o:OfficeDocumentSettings> 
                        <o:AllowPNG/> 
                      <o:PixelsPerInch>96</o:PixelsPerInch> 
                  </o:OfficeDocumentSettings> 
                  </xml> 
                    <![endif]-->
  <!--[if lte mso 11]> 
                      <style type="text/css"> 
                        .mj-outlook-group-fix { width:100% !important; } 
                  </style> 
                    <![endif]-->
  <!--[if !mso]><!-->
  <style type="text/css">
    @import url(https://fonts.googleapis.com/css?family=Nunito:normal,italic,bold&display=swap);
  </style>
  <!--<![endif]-->
  <style type="text/css">
    @media only screen and (min-width:480px) {
      .mj-column-per-100 {
        width: 100% !important;
        max-width: 100%;
      }

      .mj-column-per-33-333333333333336 {
        width: 33.333333333333336% !important;
        max-width: 33.333333333333336%;
      }
    }
  </style>
  <style media="screen and (min-width:480px)">
    .moz-text-html .mj-column-per-100 {
      width: 100% !important;
      max-width: 100%;
    }

    .moz-text-html .mj-column-per-33-333333333333336 {
      width: 33.333333333333336% !important;
      max-width: 33.333333333333336%;
    }
  </style>
  <style type="text/css">
    @media only screen and (max-width:480px) {
      table.mj-full-width-mobile {
        width: 100% !important;
      }

      td.mj-full-width-mobile {
        width: auto !important;
      }
    }
  </style>
</head>

<body style="word-spacing:normal;background-color:#EEEDEA;">
  <div style="background-color:#EEEDEA;">
    <!--[if mso | IE]><table align="center" border="0" cellpadding="0" cellspacing="0" class="" style="width:600px;" width="600" ><tr><td style="line-height:0px;font-size:0px;mso-line-height-rule:exactly;"><![endif]-->
    <div style="background:#ffffff;background-color:#ffffff;margin:0px auto;max-width:600px;">
      <table align="center" border="0" cellpadding="0" cellspacing="0" role="presentation"
        style="background:#ffffff;background-color:#ffffff;width:100%;">
        <tbody>
          <tr>
            <td
              style="border-bottom:none;border-left:none;border-right:none;border-top:10px solid #7897cc;direction:ltr;font-size:0px;padding:40px;padding-bottom:0px;padding-left:40px;padding-right:40px;padding-top:20px;text-align:center;">
              <!--[if mso | IE]><table role="presentation" border="0" cellpadding="0" cellspacing="0"><tr><td class="" style="vertical-align:top;width:520px;" ><![endif]-->
              <div class="mj-column-per-100 mj-outlook-group-fix"
                style="font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;">
                <table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%">
                  <tbody>
                    <tr>
                      <td style="vertical-align:top;padding:0;">
                        <table border="0" cellpadding="0" cellspacing="0" role="presentation" style="" width="100%">
                          <tbody>
                            <tr>
                              <td align="center" style="font-size:0px;padding:10px;word-break:break-word;">
                                <img height="auto" src="https://i.imgur.com/5FPDJmC.png"
                                  style="margin-bottom: 10px; border:0;display:block;outline:none;text-decoration:none;height:auto;width:25%;font-size:13px;"
                                  width="75px">
                                <div
                                  style="font-family:Nunito, Helvetica, Arial, sans-serif;font-size:20px;line-height:1;text-align:center;color:#7897cc;">
                                  I - Assets Management</div>
                              </td>
                            </tr>
                            <tr>
                              <td style="font-size:0px;word-break:break-word;">
                                <div style="height:5px;line-height:5px;"> </div>
                              </td>
                            </tr>
                            <tr>
                              <td align="center"
                                style="font-size:0px;padding:10px 0;padding-top:10px;padding-right:0;padding-bottom:10px;padding-left:0;word-break:break-word;">
                                <p style="border-top:solid 2px #7897cc;font-size:1px;margin:0px auto;width:100%;">
                                </p>
                                <!--[if mso | IE]><table align="center" border="0" cellpadding="0" cellspacing="0" style="border-top:solid 2px #7897cc;font-size:1px;margin:0px auto;width:520px;" role="presentation" width="520px" ><tr><td style="height:0;line-height:0;"> &nbsp; 
              
            </td></tr></table><![endif]-->
                              </td>
                            </tr>
                            <tr>
                              <td style="font-size:0px;word-break:break-word;">
                                <div style="height:30px;line-height:30px;"> </div>
                              </td>
                            </tr>
                            <tr>
                              <td align="center" style="font-size:0px;padding:10px;word-break:break-word;">
                                <div
                                  style="font-family:Nunito, Helvetica, Arial, sans-serif;font-size:48px;line-height:1.25;text-align:center;color:#231F20;">
                                  <strong>
                                    Due Date Reminder!</strong></div>
                              </td>
                            </tr>
                          </tbody>
                        </table>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <!--[if mso | IE]></td></tr></table><![endif]-->
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <!--[if mso | IE]></td></tr></table><table align="center" border="0" cellpadding="0" cellspacing="0" class="" style="width:600px;" width="600" ><tr><td style="line-height:0px;font-size:0px;mso-line-height-rule:exactly;"><![endif]-->
    <div
      style="background:#ffffff;background-color:#ffffff;margin:0px auto;border-radius:0px 0px 10px 10px;max-width:600px;">
      <table align="center" border="0" cellpadding="0" cellspacing="0" role="presentation"
        style="background:#ffffff;background-color:#ffffff;width:100%;border-radius:0px 0px 10px 10px;">
        <tbody>
          <tr>
            <td
              style="direction:ltr;font-size:0px;padding:40px;padding-bottom:0px;padding-left:50px;padding-right:50px;padding-top:0px;text-align:center;">
              <!--[if mso | IE]><table role="presentation" border="0" cellpadding="0" cellspacing="0"><tr><td class="" style="vertical-align:top;width:500px;" ><![endif]-->
              <div class="mj-column-per-100 mj-outlook-group-fix"
                style="font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;">
                <table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%">
                  <tbody>
                    <tr>
                      <td style="vertical-align:top;padding:0;">
                        <table border="0" cellpadding="0" cellspacing="0" role="presentation" style="" width="100%">
                          <tbody>
                            <tr>
                              <td align="center" style="font-size:0px;padding:10px;word-break:break-word;">
                                <div
                                  style="font-family:Nunito, Helvetica, Arial, sans-serif;font-size:24px;line-height:1.5;text-align:center;color:#231F20;">
                                  <span style="word-spacing: normal;">
                                    Hello, we like to remind you the due date for asset on assign.
                                  </span><br></div>
                              </td>
                            </tr>
                            <tr>
                              <td style="font-size:0px;word-break:break-word;">
                                <div style="height:50px;line-height:50px;"> </div>
                              </td>
                            </tr>
                            <tr>
                              <td align="left" style="font-size:0px;padding:10px;word-break:break-word;">
                                <div
                                  style="font-family:Nunito, Helvetica, Arial, sans-serif;font-size:24px;line-height:1.5;text-align:center;color:#231F20;">
                                  <span style="word-spacing: normal;">
                                    <b>Asset Name</b>
                                  </span><br>
                                  <span style="word-spacing: normal;">
                                    @asset@
                                  </span><br>
                                </div>
                              </td>
                            </tr>
                            <tr>
                              <td align="left" style="font-size:0px;padding:10px;word-break:break-word;">
                                <div
                                  style="font-family:Nunito, Helvetica, Arial, sans-serif;font-size:24px;line-height:1.5;text-align:center;color:#231F20;">
                                  <span style="word-spacing: normal;">
                                    <b>Due Date</b>
                                  </span><br>
                                  <span style="word-spacing: normal;">
                                    @date@
                                  </span><br>
                                </div>
                              </td>
                            </tr>
                            <tr>
                              <td style="font-size:0px;word-break:break-word;">
                                <div style="height:10px;line-height:10px;"> </div>
                              </td>
                            </tr>

                            <tr>
                              <td style="font-size:0px;word-break:break-word;">
                                <div style="height:30px;line-height:30px;"> </div>
                              </td>
                            </tr>
                            <tr>
                              <td align="center" style="font-size:0px;padding:0px;word-break:break-word;">
                                <table border="0" cellpadding="0" cellspacing="0" role="presentation"
                                  style="border-collapse:collapse;border-spacing:0px;" class="mj-full-width-mobile">
                                  <tbody>
                                    <tr>
                                      <td style="width:500px;" class="mj-full-width-mobile">
                                        <img height="auto" src="https://i.imgur.com/nWEhIsl.png"
                                          style="border:0;display:block;outline:none;text-decoration:none;height:auto;width:100%;font-size:13px;"
                                          width="500">
                                      </td>
                                    </tr>
                                  </tbody>
                                </table>
                              </td>
                            </tr>
                          </tbody>
                        </table>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <!--[if mso | IE]></td></tr></table><![endif]-->
            </td>
          </tr>
        </tbody>
      </table>



</body>

</html>', 0, '1', now(), true);				