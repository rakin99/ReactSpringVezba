import React from "react"

function Conditional(props){
    const loading=props.isLoading ? "Loading...":"Page is load."
    return(
        <div>
            <h1>
                {loading}
            </h1>
        </div>
    )
}

export default Conditional