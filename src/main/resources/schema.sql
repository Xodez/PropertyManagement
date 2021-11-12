CREATE TABLE property (
    eircode varchar(6) NOT NULL PRIMARY KEY,
    capacity int(4) NOT NULL,
    occupants int (4) NOT NULL,
    cost float (10) NOT NULL
);

CREATE TABLE tenants (
    email varchar(60) NOT NULL PRIMARY KEY,
    firstName varchar(30) NOT NULL,
    lastName varchar(30) NOT NULL,
    phone int(20) NOT NULL,
    eircode varchar(6),
    foreign key (eircode) REFERENCES property(eircode)
);