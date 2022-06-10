import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import './HomePage.css';

import IUD from '../assets/IUD.jpg';
import IUD2 from '../assets/IUD2.png';
import IUD3 from '../assets/IUD3.jpg';
import { Sidebar } from '../components/sidebar/Sidebar';
import {Footer} from '../components/footer/Footer';

export function HomePage(){
    return(
        <>
            <Sidebar />
            <header>
                <h1>Inventario IU Digital</h1>
            </header>
            <main>
                <div id="carouselExampleIndicators" className="carousel slide" data-bs-ride="true">
                    <div className="carousel-indicators">
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" className="active" aria-current="true" aria-label="Slide 1"></button>
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                    </div>
                    <div className="carousel-inner">
                        <div className="carousel-item active">
                            <img src={IUD}  alt="..."/>
                        </div>
                        <div className="carousel-item">
                            <img src={IUD2} alt="..."/>
                        </div>
                        <div className="carousel-item">
                            <img src={IUD3}  alt="..."/>
                        </div>
                    </div>
                    <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span className="visually-hidden">Previous</span>
                    </button>
                    <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                        <span className="carousel-control-next-icon" aria-hidden="true"></span>
                        <span className="visually-hidden">Next</span>
                    </button>
                </div>
            </main>
            <Footer />
        </>
    )
}