import React from "react"

import Login from "./Login"
import Buttons from "./Buttons"
import Main from "./Main"
import Registracija from "./Registracija"

import $ from "jquery" 
import _decodeJWT from 'jwt-decode'

class Bioskop extends React.Component{
    constructor(){
        super()
        this.state = {
            usernameRequest:"",
            passwordRequest:"",
            logedUsername:"",
            roles:"",
            NoviFilm: false,
            Users: false,
            NovaProjekcija: false,
            filmovi:[],
            projekcije: [],
            idProjekcije: false,
            mojProfil:false,
            f:false,
            izvestaji:false
        }
        this.handleClick=this.handleClick.bind(this)
        this.handleChange=this.handleChange.bind(this)
        this.handleSubmit=this.handleSubmit.bind(this)
        this.handleToUpdateAdd=this.handleToUpdateAdd.bind(this)
        this.handleToUpdateFilmovi=this.handleToUpdateFilmovi.bind(this)
        this.handleToUpdateProjekcije=this.handleToUpdateProjekcije.bind(this)
        this.handleToUpdateAddProjekcije=this.handleToUpdateAddProjekcije.bind(this)
        this.setTrue=this.setTrue.bind(this)
        this.setFalse=this.setFalse.bind(this)
    }
    
    componentDidMount(){
        $('#Registracija').hide()

    }

    handleClick(event){
        
        const name = event.target.name
      /*  $('#Filmovi').hide()
        $('#noviFilm').hide()
        $('#NoviFilm').show()*/
        this.setState({
            NoviFilm: false,
            Users: false,
            NovaProjekcija: false,
            idProjekcije: false,
            mojProfil:false,
            izvestaji:false
        })
        if(name === "logout"){
            window.location.reload()
        }else if(name === "Registracija"){
            $('#main').children().hide();
            $('#idLogin').hide()
            $('#Registracija').show()
            $('#registracija').hide()
        }else{
            $('#main').children().hide();
            this.setState({[name]:true})
            $('#'+name).show()
        }
    }

    handleChange(event){
        const {name,value} = event.target
        this.setState({[name]: value})
    }

    handleToUpdateAdd(someArg){
        this.state.filmovi.push(someArg)
        this.setState({f:true})
    }

    handleToUpdateAddProjekcije(someArg){
        this.state.projekcije.push(someArg)
        this.setState()
    }

    handleToUpdateFilmovi(someArg){
        this.setState({filmovi:someArg})
    }

    handleToUpdateProjekcije(someArg){
        this.setState({projekcije:someArg})
    }

    setTrue(){
        console.log("SetTrue")
        this.setState({idProjekcije:true})
    }
    
    setFalse(){
        this.setState({
            NovaProjekcija:false
        })
    }

    handleSubmit(e){
        e.preventDefault();
        if(this.state.usernameRequest === "" || this.state.passwordRequest === ""){
            alert("--Polja za korisnicko ime i lozinku moraju biti popunjena!--")
        }else{
            const userLogin={
                username:this.state.usernameRequest,
                password:this.state.passwordRequest
            }
            var config={
                dataType: 'json',
                method: 'post',
                body: JSON.stringify(userLogin)
              };
    
            fetch('http://localhost:8080/auth/login',config)
              .then(response => {
                    if(!response.ok){
                        alert("--Pogresno korisnicko ime ili lozinka!--")
                        window.location.reload()
                    }else{
                        return response.json()
                    }
                })
                .then(res => {
                    const jwt = res.accessToken;
                    localStorage.setItem('jwt', jwt);
                    const decodedJWTData = _decodeJWT(jwt);
                    if (decodedJWTData != null) {
                        this.setState({
                            logedUsername:decodedJWTData.sub,
                            roles:decodedJWTData.roles,
                            passwordRequest:"",
                            usernameRequest:""
                        })
                    }
                }) 
    
            $('#idLogin').hide()
        }
    }

    render(){
        const buttons=this.state.logedUsername !== "" &&  <Buttons state={this.state} handleClick={this.handleClick}/>
        const main=this.state.logedUsername !== "" && <Main setFalse={this.setFalse} setTrue={this.setTrue} state={this.state} handleToUpdateProjekcije={this.handleToUpdateProjekcije} handleToUpdateAddProjekcije={this.handleToUpdateAddProjekcije} handleToUpdateAdd={this.handleToUpdateAdd} handleToUpdateFilmovi={this.handleToUpdateFilmovi}/>
        return(
        <div id="Bioskop">
            <Login handleClick={this.handleClick} handleSubmit={this.handleSubmit} handleChange={this.handleChange} state={this.state}/>
            <Registracija/>
            {buttons}
            <br/>
            {main}
        </div>
    )}
}

export default Bioskop