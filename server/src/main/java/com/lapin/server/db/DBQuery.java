package com.lapin.server.db;

public enum DBQuery {
    CREATE_ROUTE_TABLE("CREATE TABLE IF NOT EXISTS ROUTE(\n" +
            "\tID BIGSERIAL PRIMARY KEY,\n" +
            "\tNAME VARCHAR(80) NOT NULL,\n" +
            "\tCOORD_ID BIGINT NOT NULL,\n" +
            "\tCREATION_DATE DATE NOT NULL,\n" +
            "\tLOC_FROM_ID BIGINT NOT NULL,\n" +
            "\tLOC_TO_ID BIGINT NOT NULL,\n" +
            "\tDISTANCE BIGINT NOT NULL CHECK(DISTANCE > 1),\n" +
            "\tAUTHOR_ID BIGINT NOT NULL,\n" +
            "\tFOREIGN KEY(COORD_ID) REFERENCES COORDINATES(ID) ON UPDATE CASCADE ON DELETE RESTRICT,\n" +
            "\tFOREIGN KEY(LOC_FROM_ID) REFERENCES LOCATION_FROM(ID) ON UPDATE CASCADE ON DELETE RESTRICT,\n" +
            "\tFOREIGN KEY(LOC_TO_ID) REFERENCES LOCATION_TO(ID) ON UPDATE CASCADE ON DELETE RESTRICT,\n" +
            "\tFOREIGN KEY(AUTHOR_ID) REFERENCES USERS(ID) ON UPDATE CASCADE ON DELETE CASCADE\n" +
            ")"),
    CREATE_COORDINATES_TABLE("CREATE TABLE IF NOT EXISTS COORDINATES(\n" +
            "\tID BIGSERIAL PRIMARY KEY,\n" +
            "\tCOORD_X DOUBLE PRECISION CHECK(COORD_X < 670) NOT NULL,\n" +
            "\tCOORD_Y DOUBLE PRECISION\n" +
            ") "),
    CREATE_LOCATION_FROM_TABLE("CREATE TABLE IF NOT EXISTS LOCATION_FROM(\n" +
            "\tID BIGSERIAL PRIMARY KEY,\n" +
            "\tLOC_FROM_X INT NOT NULL,\n" +
            "\tLOC_FROM_Y DOUBLE PRECISION,\n" +
            "\tLOC_FROM_Z DOUBLE PRECISION\n" +
            ")"),
    CREATE_LOCATION_TO_TABLE("CREATE TABLE IF NOT EXISTS LOCATION_TO(\n" +
            "\tID BIGSERIAL PRIMARY KEY,\n" +
            "\tLOC_TO_X DOUBLE PRECISION NOT NULL,\n" +
            "\tLOC_TO_Y DOUBLE PRECISION NOT NULL,\n" +
            "\tLOC_TO_NAME VARCHAR(80) NOT NULL\n" +
            ")"),
    CREATE_USERS_TABLE("CREATE TABLE IF NOT EXISTS USERS(\n" +
            "\tID BIGSERIAL PRIMARY KEY,\n" +
            "\tLOGIN VARCHAR(80) UNIQUE NOT NULL CHECK(LENGTH(LOGIN) >= 1),\n" +
            "\tHASH VARCHAR(256) NOT NULL CHECK(LENGTH(HASH) >= 6)\n" +
            ")"),
    INSERT_USER("INSERT INTO users (login, hash) values(?, ?) RETURNING id"),
    SELECT_USER_BY_LOGIN_AND_PASSWORD("SELECT id from users where login = ? and hash = ?"),
    SELECT_USER_BY_LOGIN("SELECT id from users where login = ?"),
    SELECT_ALL_ROUTES("SELECT ROUTE.ID, ROUTE.NAME, COORD_X, COORD_Y, CREATION_DATE,\n" +
            "LOC_FROM_X, LOC_FROM_Y, LOC_FROM_Z,\n" +
            "LOC_TO_X, LOC_TO_Y, LOC_TO_NAME,\n" +
            "DISTANCE, AUTHOR_ID FROM ROUTE\n" +
            "JOIN COORDINATES ON ROUTE.COORD_ID = COORDINATES.ID \n" +
            "JOIN LOCATION_FROM ON ROUTE.LOC_FROM_ID = LOCATION_FROM.ID\n" +
            "JOIN LOCATION_TO ON ROUTE.LOC_TO_ID = LOCATION_TO.ID");
    private final String query;

    DBQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
