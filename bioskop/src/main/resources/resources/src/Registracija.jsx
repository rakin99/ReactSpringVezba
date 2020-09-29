import React from "react"

import RegistracijaForm from "./RegistracijaForm"

class Registracija extends React.Component{

    constructor(){
        super()
        var currentdate = new Date(); 
        const datetime = currentdate.getDate() + "-"
                + (currentdate.getMonth()+1)  + "-" 
                + currentdate.getFullYear()
        this.state = {
            korisnickoIme : "",
            lozinka: "",
            datumRegistracije: datetime,
            uloga: "ROLE_USER",
        }
        this.handleChange = this.handleChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleClick = this.handleClick.bind(this)
    }

    handleChange(event){
        const {name, value} = event.target
        this.setState({
            [name]:value
        })
    }

    handleClick(){
        window.location.reload()
    }

    handleSubmit(e){
        e.preventDefault();
        if(this.state.korisnickoIme === "" || this.state.lozinka === ""){
            alert("--Sva polja moraju biti popunjena!--")
        }else{
            var config={
                dataType: 'json',
                method: 'post',
                body: JSON.stringify(this.state)
              };
    
              console.log(config.body)
    
            // On submit of the form, send a POST request with the data to the server.
            fetch('http://localhost:8080/auth/signup',config)
                .then(res => res.json())
                .then(res => {
                    if(res.result==="success"){
                        alert("--Uspešno ste se registrovali!--")
                        window.location.reload();
                    }else{
                        alert("--Doslo je do greske prilikom registracije!--")
                    }
                })
        }
      }

    render(){
        return(
            <div id="Registracija">
                <RegistracijaForm handleClick={this.handleClick} handleChange={this.handleChange} handleSubmit={this.handleSubmit} state={this.state}/>
            </div>
        )
    }
}

export default Registracija