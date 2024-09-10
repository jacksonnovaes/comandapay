# Comanda pay projeto fullstack mono repo

Aplicatido mobile desenvolvido em react-native para gerenciar comandas de bares e/oou restaurantes, nela o cliente possui um cadastro e uma lista de estabelecimentos para utilizar o cardapio e efetuar compras de consumo previamente.

A ideia e ganhar agilidade no fechamneto de comandas e no gerenciamento delas.

Funsionalidades

## Favoritar estabelecimento

O usuario do aplicativo podera favoritar seus locais e desta maneira encontrar seus estabelecimento com maior agilidade.
![image](https://github.com/user-attachments/assets/3c5e8e8c-f72a-4d47-9db9-44b00ca8cab3)

## visualizar cardapio por categoria

Aqui e possivelvisualizar o cardapio pela categoria selecionada
![image](https://github.com/user-attachments/assets/723d3b24-a181-4205-b515-56867ef06fe6)


## abrir uma comanda 

Com os produtos desejados em mente o cliente abre uma comanda no estabelecimento e paga pela quantidade que ir consumeit e somente quaando desejar ir embora fecha a comanda com todos os items pagos

![image](https://github.com/user-attachments/assets/3e6ef358-3bda-4fd9-8dcb-c26c7b6a4b74)


## escolha do metodode pagamento

# pix
Geracao de QR code para pagar o item consumido
![image](https://github.com/user-attachments/assets/8c29ba6e-f58b-4824-9c6d-4d4fd8074e28)


# Cartao de credito (em breve)
sera possivel cadastrar o cartao de credito e efetuar o pagamento dos items consumidos.

# Wallet (em breve)
sera possivel adicionar na casteira digital do app creditos para serem consumidos 

### Como utilziar a Api?

Baixe o monorepo aqui: 
`$ git clone git@github.com:jacksonnovaes/comandapay.git`

acesso a pasta comanda-pay
`$ cd comanda-pay` 

# docker compose
Temos 3 arquivos compose para rodar localmente bas altetar o profile do arquivo application.properties para `local` e digitar na linha de comando
`$ docker compose --file docker-compose-local.yaml up`

