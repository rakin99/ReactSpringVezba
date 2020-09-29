import React from "react"

function TabelaKarte(props){
    const karte= props.state.karte.map(k => <tr key={k.id}>
                                                <td>{k.datumVremeProdaje}</td>
                                                <td><button name={k.id} onClick={props.handleClick} style={{color: "blue"}}>Prikazi kartu</button></td>
                                            </tr>
    )
    return(
        <div id="tabelaKarata">
            <table>
                <thead>
                    <tr>
                        <th>Vreme prodaje karte:</th>
                    </tr>
                </thead>
                <tbody>
                    {karte}
                </tbody>
            </table>
        </div>
    )
}
export default TabelaKarte