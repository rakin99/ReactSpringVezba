import React from "react"

function PrikazFilmova(props){
    return(
            <tr>
                <td><button style={{color: "blue"}} onClick={props.handleClick} name={props.film.id}>{props.film.naziv}</button></td>
                <td>{props.film.zanrovi}</td>
                <td>{props.film.trajanje}</td>
                <td>{props.film.distributer}</td>
                <td>{props.film.zemljaPorekla}</td>
                <td>{props.film.godinaProizvodnje}</td>
            </tr>
    )

}

export default PrikazFilmova