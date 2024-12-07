package lms.spring61ref;

import java.util.List;

public class Sort {

    public List<String> sortByLength(List<String> list) {
        list.sort((a, b) -> a.length() - b.length());
        return list;
    }

}
