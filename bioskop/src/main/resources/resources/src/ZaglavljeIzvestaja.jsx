import React from "react"

function ZaglavljeIzvestaja(props){
    return(
        <table>
            <tbody>
                <tr>
                    <td>Pocetak izvestaja:</td>
                    <td><input onChange={props.handleChange} type="datetime-local" name="pocetak"/></td>
                </tr>
                <tr>
                    <td>Kraj izvestaja:</td>
                    <td><input onChange={props.handleChange} type="datetime-local" name="kraj"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td style={{textAlign:"right"}}><button onClick={props.handleClick}>Kreiraj izvestaj</button></td>
                </tr>
            </tbody>
        </table>
    )
}

export default ZaglavljeIzvestaja