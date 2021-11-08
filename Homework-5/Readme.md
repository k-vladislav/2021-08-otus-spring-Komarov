приложение хранящее информацию о книгах в библиотеке


    //todo delete
    public void getAllNew() {
        String query = "select b.title, a.last_name, g.genre\n" +
                "from book b\n" +
                "left join book_author ba on (b.id=ba.book_id)\n" +
                "left join book_genre bg on (b.id=bg.book_id)\n" +
                "left join author a on (a.id=ba.author_id)\n" +
                "left join genre g on (g.id=bg.genre_id)";

        SqlRowSet sqlRowSet = jdbc.queryForRowSet(query, Map.of());
        while (sqlRowSet.next()) {
            StringJoiner stringJoiner = new StringJoiner(", ");
            stringJoiner.add("book");
            stringJoiner.add(sqlRowSet.getString("title"));
            stringJoiner.add(sqlRowSet.getString("last_name"));
            stringJoiner.add(sqlRowSet.getString("genre"));
            System.out.println(stringJoiner);
        }
    }