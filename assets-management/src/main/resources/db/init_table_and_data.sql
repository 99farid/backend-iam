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
	
INSERT INTO permissions (code, permission_name, ver, created_by, created_date, is_active)
			VALUES	('CTU','Create Table User', 0, '1', NOW(), TRUE),
					('CTA','Create Table Asset', 0, '1', NOW(), TRUE),
					('CTP','Create Table Profile', 0, '1', NOW(), TRUE),
					('UTU','Update Table User', 0, '1', NOW(), TRUE),
					('DTU','Delete Table User', 0, '1', NOW(), TRUE);

INSERT INTO roles (code, role_name, ver, created_by, created_date, is_active)
			VALUES	('RL1', 'Super Admin', 0, '1', NOW(), TRUE),
					('RL2', 'Non Admin', 0, '1', NOW(), TRUE),
					('RL3', 'Admin', 0, '1', NOW(), TRUE),
					('SYS', 'SYSTEM', 0, '1', NOW(), TRUE);
					
INSERT INTO companies (code, company_name, ver, created_by, created_date, is_active)
			VALUES	('LWN', 'Lawencon', 0, '1', NOW(), TRUE),
					('LNV', 'Linov', 0, '1', NOW(), TRUE);
				
INSERT INTO role_permissions (id_role, id_permission, ver, created_by, created_date, is_active)
			VALUES	((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'CTU'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'CTA'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL1'), (SELECT id FROM permissions WHERE code = 'DTU'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL2'), (SELECT id FROM permissions WHERE code = 'CTA'), 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL3'), (SELECT id FROM permissions WHERE code = 'CTP'), 0, '1', NOW(), TRUE);
				
INSERT INTO users (id_role, email, pass, ver, created_by, created_date, is_active)
			VALUES	((SELECT id FROM roles WHERE code = 'RL1'), 'john123@gmail.com','12345', 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL2'), 'shani56@gmail.com','12345', 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'RL3'), 'trianaayu@gmail.com','12345', 0, '1', NOW(), TRUE),
					((SELECT id FROM roles WHERE code = 'SYS'), 'SYSTEM','12345', 0, '1', NOW(), TRUE);

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
					