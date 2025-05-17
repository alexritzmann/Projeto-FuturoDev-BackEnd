# ProjetoFuturoDev - Alex Ritzmann

# ReciclaVille - API Backend

[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://java.com)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1-green.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue.svg)](https://www.postgresql.org)

API para gerenciamento de compensação de carbono para empresas parceiras.

## 📋 Tabela de Conteúdos
- [Introdução]
- [Pré-requisitos]
- [Autenticação]
- [Endpoints]
  - [Login]
  - [Usuários]
  - [Clientes]
  - [Materiais]
  - [Declarações]
  - [Dashboard]
- [Regras de Acesso]

## 🌟 Introdução
O ReciclaVille é uma plataforma que permite:
- Cadastro de declarações de embalagens
- Cálculo de compensação de carbono
- Gestão de materiais e clientes
- Visualização de dados através de dashboard

## 🛠️ Pré-requisitos
- Java 17
- Maven 3.8+
- PostgreSQL 15+
- Postman ou similar

## 🔒 Autenticação
Todos endpoints (exceto login) requerem token JWT no header
(Authorization: Bearer <token>)

Usuário inicial padrão
{
  "username": "admin",
  "password": "admin"
}

## 📡 Endpoints

### 🔑 Login
```
**`POST /login`**  
Endpoint público para autenticação

Request
json
{
  "username": "admin",
  "password": "admin"
}

Response (200):
json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "username": "admin",
  "role": "ADMIN",
  "clientName": null
}
```
### Usuários
```
Endpoint: POST /users
Autenticação: Requer ADMIN

Request:
json
{
  "name": "Novo Usuário",
  "username": "username",
  "password": "user",
  "role": "USER",
  "client": {
    "id": 1
  }
}

Response (201):
json
{
  "id": 2,
  "name": "Novo Usuário",
  "username": "username",
  "role": "USER",
  "client": {
    "id": 1,
    "name": "Empresa X"
  }
}
```
### Clientes
```
Endpoint: POST /clients
Autenticação: Requer ADMIN

Request:
json
{
  "name": "Empresa X",
  "cnpj": "11222333000144",
  "economicActivity": "Indústria de plástico",
  "responsible": "Alex R."
}

Response (201 Created):
json
{
  "id": 1,
  "name": "Empresa X",
  "cnpj": "11222333000144",
  "economicActivity": "Indústria de plástico",
  "responsible": "Alex R."
}
```
### Materiais
```
Endpoint: POST /materials
Autenticação: Requer ADMIN

Request:
json
{
  "name": "Plástico PET",
  "percentageCompensation": 0.75
}

Response (201 Created):
json
{
  "id": 5,
  "name": "Plástico PET",
  "percentageCompensation":  0.75
}

```
### Declarações
```
Endpoint: POST /declarations
Autenticação: Requer ADMIN ou USER

Request:
json
{
  "clientId": 1,
  "dateStartPeriod": "2025-04-01",
  "dateFinalPeriod": "2025-04-31",
  "items": [
    {
      "materialId": 5,
      "tonsDeclared": 1500.50
    }
  ]
}

Response (201 Created):
json
{
  "id": 8,
  "dateDeclaration": "2025-05-19",
  "dateStartPeriod": "2025-04-01",
  "dateFinalPeriod": "2025-04-31",
  "totalMaterials": 1500.50,
  "totalCompensation": 11.25,
  "client": {
    "id": 1,
    "name": "Empresa X"
  },
  "items": [
    {
      "id": 13,
      "material": {
        "id": 5,
        "name": "Plástico PET"
      },
      "tonsDeclared": 1500.50,
      "tonsCompensation": 11.25
    }
  ]
}
```
### Dashboard
```
Endpoint: GET /dashboard
Autenticação: Requer ADMIN ou USER

Response (200):
json
[
  {
    "material": "Plástico PET",
    "totalCompensation": 11.25
  }
]
```
## 🔐 Regras de Acesso Detalhadas

### 👨💻 Tipos de Usuários
| Role  | Descrição                                                                 |
|-------|---------------------------------------------------------------------------|
| ADMIN | Acesso total ao sistema, gestão de todos recursos e usuários              |
| USER  | Acesso restrito aos recursos relacionados ao seu cliente associado        |

---

### 📋 Permissões
#### Autenticação
| Endpoint | Método | Permissões | Observações                              |
|----------|--------|------------|------------------------------------------|
| `/login` | POST   | Público    | Único endpoint sem autenticação requerida |

#### Usuários
| Endpoint       | Método | Permissões           | Regras Específicas                              |
|----------------|--------|----------------------|-------------------------------------------------|
| `/users`       | GET    | ADMIN                | Lista todos usuários                            |
| `/users`       | POST   | ADMIN                | Cria novos usuários                             |
| `/users/{id}`  | GET    | ADMIN, USER          | USER só pode ver seu próprio registro           |
| `/users/{id}`  | PUT    | ADMIN, USER          | USER só pode atualizar seu próprio registro     |
| `/users/{id}`  | DELETE | ADMIN                | Apenas administradores podem remover usuários   |

#### Clientes
| Endpoint         | Método | Permissões | Regras Específicas                              |
|------------------|--------|------------|-------------------------------------------------|
| `/clients`       | GET    | ADMIN      | Lista todos clientes                            |
| `/clients`       | POST   | ADMIN      | Cria novos clientes                             |
| `/clients/{id}`  | GET    | ADMIN      | Visualiza detalhes de um cliente                |
| `/clients/{id}`  | PUT    | ADMIN      | Atualiza dados de clientes                      |
| `/clients/{id}`  | DELETE | ADMIN      | Remove clientes                                 |

#### Materiais
| Endpoint           | Método | Permissões | Regras Específicas                                  |
|--------------------|--------|----------------|-------------------------------------------------|
| `/materials`       | GET    | ADMIN, USER    | Lista materiais disponíveis                     |
| `/materials`       | POST   | ADMIN          | Cria novos materiais                            |
| `/materials/{id}`  | PUT    | ADMIN          | Atualiza dados de materiais                     |
| `/materials/{id}`  | DELETE | ADMIN          | Remove materiais (se não estiver em uso)        |

#### Declarações
| Endpoint             | Método | Permissões       | Regras Específicas                              |
|----------------------|--------|------------------|-------------------------------------------------|
| `/declarations`      | GET    | ADMIN, USER      | USER vê apenas declarações do seu cliente       |
| `/declarations`      | POST   | ADMIN, USER      | USER só pode criar declaração para seu cliente  |
| `/declarations/{id}` | GET    | ADMIN, USER      | USER só pode ver declaração do seu cliente      |
| `/declarations/{id}` | PUT    | ADMIN, USER      | USER só pode editar declaração do seu cliente   |
| `/declarations/{id}` | DELETE | ADMIN, USER      | USER só pode excluir declaração do seu cliente  |

#### Dashboard
| Endpoint      | Método | Permissões       | Regras Específicas                              |
|---------------|--------|------------------|-------------------------------------------------|
| `/dashboard`  | GET    | ADMIN, USER      | USER vê dados apenas do seu cliente             |

---
