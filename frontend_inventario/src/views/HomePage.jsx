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
            <main>
                <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="true">
                    <div class="carousel-indicators">
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                    </div>
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src={IUD}  alt="..."/>
                        </div>
                        <div class="carousel-item">
                            <img src={IUD2} alt="..."/>
                        </div>
                        <div class="carousel-item">
                            <img src={IUD3}  alt="..."/>
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </main>
            <Footer />
        </>
    )
}