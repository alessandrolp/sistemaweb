package br.com.sistema.controller;

import br.com.sistema.model.dao.HibernateDAO;
import br.com.sistema.model.dao.InterfaceDAO;
import br.com.sistema.model.entities.Cidade;
import br.com.sistema.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mbCidade")
@RequestScoped
public class MbCidade implements Serializable {

    private static final long serialVersionUID = 1L;

    private Cidade cidade = new Cidade();
    private List<Cidade> cidades;

    public MbCidade() {
    }

    private InterfaceDAO<Cidade> cidadeDAO() {
        InterfaceDAO<Cidade> cidadeDAO = new HibernateDAO<Cidade>(Cidade.class, FacesContextUtil.getRequestSession());
        return cidadeDAO;
    }

    public String limpaCidade() {
        cidade = new Cidade();
        return "/restrict/cadastrarcidade.faces";
    }

    public String editCidade() {
        return "/restrict/cadastrarcidade.faces";
    }

    public String addCidade() {
        if (cidade.getIdCidade() == null || cidade.getIdCidade() == 0) {
            inserirCidade();
        } else {
            updateCidade();
        }
        limpaCidade();
        return null;
    }

    private void inserirCidade() {
        cidadeDAO().save(cidade);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuado com sucesso", ""));
    }

    private void updateCidade() {
        cidadeDAO().update(cidade);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuado com sucesso", ""));
    }

    public void deleteCidade() {
        cidadeDAO().remove(cidade);
    }

    public List<Cidade> getCidades() {
        cidades = cidadeDAO().getEntities();
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

}
