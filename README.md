# Documentação da API

Esta documentação fornece informações sobre como utilizar a API disponibilizada. A API permite realizar operações CRUD em recursos específicos. 


[link Postman](https://documenter.getpostman.com/view/7221853/2sA3rwNaQF)

## Configuração de BD para testes com H2 - Teste de Repository
### Inserir Depêndencia no POM.xml
```bash
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>test</scope>
</dependency>
```

[application-test.properties para configurar o H2](https://github.com/matielojg/4p-springboot-crud-back/blob/main/urna-virtual-uniamerica/src/test/resources/application-test.properties)

OBS: cuidar com a estrutura de pastas onde vai ficar seu arquivo 
```bash
urna-virtual-uniamerica/src/test/resources/application-test.properties
```
### Configuração para o Repository testar no banco H2 ao invés do banco Mysql
```bash
@DataJpaTest
@ActiveProfiles("test")  // Use um profile de test para não interferir no banco real
public class CandidatoRepositoryTest {
```
