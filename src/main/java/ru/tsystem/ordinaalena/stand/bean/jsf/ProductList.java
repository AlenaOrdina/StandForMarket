package ru.tsystem.ordinaalena.stand.bean.jsf;

import ru.tsystem.ordinaalena.stand.bean.ejb.TopProducts;
import ru.tsystem.ordinaalena.stand.model.Product;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class ProductList implements Serializable {
    private static final long serialVersionUID=1L;
    @EJB
    private TopProducts topProducts;

    public List<Product> getTopProducts(){

        return topProducts.getTops();
    }
}
