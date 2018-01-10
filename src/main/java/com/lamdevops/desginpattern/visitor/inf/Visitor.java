package com.lamdevops.desginpattern.visitor.inf;

import com.lamdevops.desginpattern.visitor.model.Book;
import com.lamdevops.desginpattern.visitor.model.CD;
import com.lamdevops.desginpattern.visitor.model.DVD;

public interface Visitor {

    void visit(Book book);
    void visit(CD cd);
    void visit(DVD dvd);
}
