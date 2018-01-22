package com.lamdevops.desginpattern.visitor.PostageFees.inf;

import com.lamdevops.desginpattern.visitor.PostageFees.model.Book;
import com.lamdevops.desginpattern.visitor.PostageFees.model.CD;
import com.lamdevops.desginpattern.visitor.PostageFees.model.DVD;

public interface Visitor {

    void visit(Book book);
    void visit(CD cd);
    void visit(DVD dvd);
}
