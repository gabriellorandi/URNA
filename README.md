## URNA

Um sistema de votação com a possibilidade de cadastrar eleitores, cadidatos, mesários, chapas além de adminstrar a eleição e seus votos.

## Objetivo

Demonstrar os conhecimentos de Java sem framework no banco de dados Postgresql, utilizando conhecimentos como Clean Code e MVC.

## Tecnológias utilizadas

- [Java 8](https://java.com/)
- [Postgresql](https://www.postgresql.org/)
- JSP

## Install
### Linux
#### Java

```shell
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt install oracle-java8-installer
```
## Exemplo de código

```Java
public void importar() throws IOException {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());

        Secao secao = (Secao) cbSecao.getSelectionModel().getSelectedItem();

        if(secao == null) {
            AlertUtils.alert("informação faltando","Por favor selecionar uma seção para cadastro", Alert.AlertType.WARNING);
            return;
        }

        if(file != null) {

            List<Eleitor> eleitores = ImportUtils.loadEleitores(file);

            for(Eleitor eleitor : eleitores) {
                eleitor.setSecao(secao);
                eleitorDAO.cadastrarEleitor(eleitor,eleicao);
            }

            eleitores = eleitorDAO.selecionarEleitores(eleicao);
            tableView.getItems().setAll(eleitores);
            tableView.refresh();

        }

    }
```

## Contribuidores

- [Thiago Henrique](https://github.com/thn99)
- [Gabriel Lorandi](https://www.linkedin.com/in/gabriel-lorandi/)
- [Brendow Podsclan](https://github.com/Podsclan)

