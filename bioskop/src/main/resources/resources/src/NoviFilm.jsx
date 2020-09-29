import React from "react"
import $ from "jquery"

import FilmForm from "./FilmForm"

class NoviFilm extends React.Component{
    constructor(){
        super()
        this.state = {
            naziv: "",
            reziser: "",
            glumci: "",
            zanrovi: "",
            trajanje: "",
            distributer: "",
            zemljaPorekla: "",
            godinaProizvodnje: "",
            opis: ""
        }
        this.handleChange=this.handleChange.bind(this)
        this.handleSubmit=this.handleSubmit.bind(this)
        this.cancel=this.cancel.bind(this)
    }

    handleChange(event){
        const {name, value} = event.target
        this.setState({[name]:value})
    } 

    handleSubmit(e){
        e.preventDefault();

        if(this.state.naziv === "" || this.state.reziser === "" || this.state.glumci === "" || 
            this.state.zanrovi === "" || this.state.trajanje==="" || this.state.distributer === "" || 
            this.state.zemljaPorekla === "" || this.state.godinaProizvodnje === "" || this.state.opis === ""){
                alert("--Sva polja moraju biti popunjena!--")
            }else{
                var config={
                    dataType: 'json',
                    contentType:"application/json; charset=utf-8",
                    method: 'post',
                    headers: {'Authorization': 'Bearer ' + localStorage.getItem('jwt')},
                    body: JSON.stringify(this.state)
                  };
                  console.log("SALJEM: "+JSON.stringify(this.state))
        
                // On submit of the form, send a POST request with the data to the server.
                fetch('http://localhost:8080/bioskop/filmovi/add',config)
                    .then(res => res.json())
                    .then(res => {
                        if(res.result !== "success"){
                            alert(res.result)
                        }else if(res.result === "success"){
                            const film={
                                id:res.id,
                                naziv: this.state.naziv,
                                reziser: this.state.reziser,
                                glumci: this.state.glumci,
                                zanrovi: this.state.zanrovi,
                                trajanje: this.state.trajanje,
                                distributer: this.state.distributer,
                                zemljaPorekla: this.state.zemljaPorekla,
                                godinaProizvodnje: this.state.godinaProizvodnje,
                                opis: this.state.opis
                            }
                            this.props.handleToUpdateAdd(film)

                            this.setState({
                                naziv: "",
                                reziser: "",
                                glumci: "",
                                zanrovi: "",
                                trajanje: "",
                                distributer: "",
                                zemljaPorekla: "",
                                godinaProizvodnje: "",
                                opis: ""
                            })
                            
                            this.cancel()
                            alert("Uspesno ste dodali film!")
                        }
                    })
            }
      }

      cancel(){
        $('#Filmovi').show();
        $('#NoviFilm').hide();
      }

    render(){
        return(
            <div id="NoviFilm">
                <FilmForm handleChange={this.handleChange} handleSubmit={this.handleSubmit} state={this.state}/>
            </div>
        )
    }

}

export default NoviFilm