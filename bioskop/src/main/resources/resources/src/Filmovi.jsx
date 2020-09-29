import React, {Component} from "react"

import FilterFilmova from "./FilterFilmova"
import PrikazFilmova from "./PrikazFilmova"
import Film from "./Film"
import EditFilm from "./EditFilm"

import $ from "jquery"

class Filmovi extends Component{
    
    constructor(){
        super()
        this.state = {
            filmovi: [],
            film:"",
            editFilm:""
        }
        this.filter=this.filter.bind(this)
        this.handleClick=this.handleClick.bind(this)
        this.deleteFilm=this.deleteFilm.bind(this)
        this.editFilm=this.editFilm.bind(this)
        this.handleChange=this.handleChange.bind(this)
        this.cancel=this.cancel.bind(this)
        this.handleSubmit=this.handleSubmit.bind(this)
    }
    
    componentDidMount(){
        const conf={
            headers: new Headers({'Authorization': 'Bearer ' + localStorage.getItem('jwt')},
                                  {'Access-Control-Allow-Methods':'GET'}),
          };
        fetch('http://localhost:8080/bioskop/filmovi',conf)
            .then(res => res.json())
            .then(response => {
                this.props.handleToUpdateFilmovi(response)
                this.setState({
                    filmovi: response
            })
        })
    }

    filter(){

        const config={
            headers: new Headers({'Authorization': 'Bearer ' + localStorage.getItem('jwt')},
                                  {'Access-Control-Allow-Methods':'GET'}),
          };

        const naziv=$('#idNaziv').val() === "" ? "null":$('#idNaziv').val();
        const zanr=$('#idZanr').val() === "" ? "null":$('#idZanr').val();
        const idOd=$('#idOd').val() === "" ? "null":$('#idOd').val();
        const idDo=$('#idDo').val() === "" ? "null":$('#idDo').val();
        const dist=$('#idDist').val() === "" ? "null":$('#idDist').val();
        const zemlja=$('#idZemlja').val() === "" ? "null":$('#idZemlja').val();
        const idGodOd=$('#idGodOd').val() === "" ? "null":$('#idGodOd').val();
        const idGodDo=$('#idGodDo').val() === "" ? "null":$('#idGodDo').val();
        const sort=$('#idSortFilmovi').val() === "" ? "null":$('#idSortFilmovi').val();

        fetch('http://localhost:8080/bioskop/filmovi/'+naziv+"/"+zanr+"/"+idOd+"/"+idDo+"/"+dist+"/"+zemlja+"/"+idGodOd+"/"+idGodDo+"/"+sort,config)
            .then(res => res.json())
            .then(res => {
                this.setState({
                    filmovi: res
                })
            })
    }

    handleChange(event){
        console.log("Handle change")
        const {name, value} = event.target
        this.setState(prevState => {
            const updateFilmovi=prevState.filmovi.map(f => {
                if(f.id == this.state.editFilm.id){
                    f[name]=value
                    prevState.editFilm=f
                }
                return f;
            })
            return {
                filmovi: updateFilmovi
            }
        })
    }

    handleClick(event){
        const id=event.target.name
        this.state.filmovi.map(f => {
            if(f.id == id){
                this.setState({film:f})
            }
        })
        $('#tableFilmovi').hide();
    }

    deleteFilm(event){
        const id = event.target.name
        var config={
            method: 'delete',
            headers: {'Authorization': 'Bearer ' + localStorage.getItem('jwt')}
          };

        fetch('http://localhost:8080/bioskop/filmovi/delete/'+id,config)
        
        this.setState(prevState => {
            const updateFilmovi=prevState.filmovi.filter(f => f.id != id)
            this.props.handleToUpdateFilmovi(updateFilmovi)
            return {
                filmovi: updateFilmovi
            }
        })
        
        this.cancel()
    }

    cancel(){
        $('#Bioskop').children('#idFilm').remove()
        $('#Bioskop').children('#editFilm').remove()
        this.setState({
            film:"",
            editFilm:""
        })
        $('#tableFilmovi').show()
    }

    editFilm(event){
        this.setState({
            editFilm: ""
        })
        const id=event.target.name
        this.state.filmovi.map(f => {
            if(f.id == id){
                this.setState({editFilm:f})
            }
        })
        $('#idFilm').hide()
    }

    handleSubmit(){
        var config={
            dataType: 'json',
            method: 'put',
            headers: {'Authorization': 'Bearer ' + localStorage.getItem('jwt')},
            body: JSON.stringify(this.state.editFilm)
          };

        fetch('http://localhost:8080/bioskop/filmovi/edit',config)
            .then(res => res.json())
            .then(res => console.log(res))

        this.cancel()
    }

    render(){
        const prikaz=this.state.filmovi.map(f => <PrikazFilmova handleClick={this.handleClick} key={f.id} film={f} />)
        const prikazFilma=this.state.film !== "" && <Film state={this.props.state} cancel={this.cancel} editFilm={this.editFilm} deleteFilm={this.deleteFilm} film={this.state.film} />
        const editFilma=this.state.editFilm !== "" && <EditFilm handleSubmit={this.handleSubmit} 
                                                        cancel={this.cancel} handleChange={this.handleChange} 
                                                        film={this.state.editFilm} />
        return(
            <div id="Filmovi">
                <div id="tableFilmovi">
                    <label>
                        Sortiraj po:
                            <select id="idSortFilmovi" onChange={this.filter}>
                                <option value=""></option>
                                <option value="n-asc">Nazivu, rastuce</option>
                                <option value="n-desc">Nazivu, opadajuce</option>
                                <option value="z-asc">Zanru, rastuce</option>
                                <option value="z-desc">Zanru, opadajuce</option>
                                <option value="t-asc">Trajanju, rastuce</option>
                                <option value="t-desc">Trajanju, opadajuce</option>
                                <option value="d-asc">Distributeru, rastuce</option>
                                <option value="d-desc">Distributeru, opadajuce</option>
                                <option value="zm-asc">Zemlji porekla, rastuce</option>
                                <option value="zm-desc">Zemlji porekla, opadajuce</option>
                                <option value="g-asc">Godini proizvodnje, rastuce</option>
                                <option value="g-desc">Godini proizvodnje, opadajuce</option>
                            </select>
                    </label>
                    <table border="1">
                        <FilterFilmova filter={this.filter} />
                        <tbody>
                            {prikaz}
                        </tbody>
                    </table>
                </div>
                {prikazFilma}
                {editFilma}
            </div>
        )
    }

}

export default Filmovi