import './teste.css';
import React, { useState } from "react";

function App() {
  const [nome, setNome] = useState("");
  const [senha, setSenha] = useState("");
  const [cnpj, setCnpj] = useState("");
  const [checked, setChecked] = useState(false);

  const [nomeLogin, setNomeLogin] = useState("");
  const [senhaLogin, setSenhaLogin] = useState("");

  console.log(nomeLogin);
  console.log(senhaLogin);

  function changeCheck () {
    setChecked(!checked);
  }

  function limparFormulario() {
    setNome("");
    setSenha("");
    setCnpj("");
    if (checked === true) {
      setChecked(false);
    }
  }

  console.log(checked);
  console.log(cnpj);

  function dadosCadastro() {
    const dadosUsuario = {
      nome: nome,
      empreendedor: checked,
      cnpj: cnpj,
      senha: senha,
    };

    fetch('http://localhost:8080/cadastrar', {
      method: 'post',
      body:JSON.stringify(dadosUsuario),
      headers: {
        'Content-type':'application/json',
        'Accept':'application/json'
      }
    })
    .then(dadosObtidos => dadosObtidos.json())
    .then(dadosObtidosConvertido => {
      if (dadosObtidosConvertido.mensagem === "Erro, o usuário empreendedor precisa definir um CNPJ.") {
        alert(dadosObtidosConvertido.mensagem);
      } else if (dadosObtidosConvertido.mensagem === "Este CNPJ já foi cadastrado.") {
        alert(dadosObtidosConvertido.mensagem);
      } else {
        alert("Cadastro efetuado com sucesso.");
        limparFormulario();
      }
    }) 
}

  

  function logar() {
    const dadosLogin = {
      nome: nomeLogin,
      senha: senhaLogin,
    };

    fetch('http://localhost:8080/login', {
      method: 'post',
      body:JSON.stringify(dadosLogin),
      headers: {
        'Content-type':'application/json',
        'Accept':'application/json'
      }
    })
    .then(dadosLoginObtidos => dadosLoginObtidos.json())
    .then(dadosLoginObtidosConvertido => {
      alert(dadosLoginObtidosConvertido.mensagem);
    })
  }

  return (
    <div className='teste'>
      <label>
        Nome:
        <br></br>
        <input type="text" id="nome" autoComplete='off' value={nome} onChange={(event) => setNome(event.target.value)}/>
      </label>
      <br></br>
      <label>
        Senha:
        <br></br>
        <input type="password" id="senha" value={senha} onChange={(event) => setSenha(event.target.value)}/>
      </label>
      <br></br>
      <label>
        <input type="checkbox" id="checkbox" onChange={() => changeCheck()}/>
        Desejo empreender
      </label>
      <br></br>
      {
        checked ? <input type="number" id="cnpj" placeholder="CNPJ" value={cnpj} onChange={(event) => setCnpj(event.target.value)}/> : undefined
      }
      <br></br>
      <button type="submit" id="submit" onClick={() => dadosCadastro()}>Cadastrar</button>
      
      <br></br>
        <input type="text" id="loginUsuario" autoComplete='off'  value={nomeLogin}  onChange={(event) => setNomeLogin(event.target.value)}/><br></br>
        <input type="password" id="senhaLogin" value={senhaLogin}  onChange={(event) => setSenhaLogin(event.target.value)}/><br></br>
      <button type="submit" id="submitLogin" onClick={() => logar()}>Logar</button>
    </div>
  );
}

export default App;
