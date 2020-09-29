import React from "react"

function AuthHeader(){
    const user = JSON.parse(localStorage.getItem('jwt'));
  
    if (user && user.accessToken) {
      return { Authorization: 'Bearer ' + user.accessToken };
    } else {
      return {};
    }
}

export default AuthHeader