package com.bureau.util;

public class SqlQuery {
    public static final String SELECT_USER_PROJECTS =
            """
            SELECT * FROM projects p
            LEFT JOIN user_projects up
            ON p.id = up.project_id
            WHERE up.user_id = ?
            """;

    public static final String SELECT_PROJECTS_BETWEEN =
            """
            SELECT * FROM projects p
            WHERE p.date BETWEEN ? AND ?
            """;
}
