import {FaGithub, FaFacebook, FaInstagram, FaYoutube} from 'react-icons/fa'

import './Footer.css';
import { Link } from 'react-router-dom';

export function Footer(){
    return (
        <>
            <div className="footer  position-absolute" >
                <div className="redes" >
                    <a href=''><FaGithub/></a>
                    <a href=''><FaFacebook/></a>
                    <a href=''><FaInstagram/></a>
                    <a href=''><FaYoutube/></a>
                </div>
                <div className="footer_info" >
                    <p>&copy; IU Digital</p>
                </div>
            </div>
        </>
        )
}