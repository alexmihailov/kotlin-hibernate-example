create table owner (
    id      int             not null,
    data    varchar2(100)   null,
    constraint owner_pk primary key(id)
);
create table child (
    id          int             not null,
    owner_id    int             not null,
    data    varchar2(100)       null,
    constraint child_pk primary key(id),
    constraint fk_owner_child foreign key(owner_id) references owner(id)
);
