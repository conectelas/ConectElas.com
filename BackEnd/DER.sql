create database db_projeto_integrador;

use db_projeto_integrador;

create table tb_usuarios(
	id bigint auto_increment,
    nome_completo varchar(64) not null,
    email varchar(128) not null,
    senha varchar(1024) not null,
    
    primary key(id)
);

create table tb_postagens (
	id bigint auto_increment,
    usuario_id bigint not null,
    data_postagem timestamp not null,
    descricao text not null,
    titulo text not null,
    foto text,
    
    primary key(id),
    foreign key(usuario_id) references tb_usuarios(id)
);

create table tb_temas(
	id bigint auto_increment,
    postagem_id bigint not null,
    usuario_id bigint not null,
    home boolean not null,
    empregabilidade boolean not null,
    marketplace boolean not null,
    
    primary key(id),
    foreign key(postagem_id) references tb_postagens(id),
    foreign key(usuario_id) references tb_usuarios(id)
);