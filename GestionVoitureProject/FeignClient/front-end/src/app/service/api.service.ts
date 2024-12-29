import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http : HttpClient) { }

  private baseUrl = 'http://localhost:8888/voituress'; 
  private baseClientUrl = 'http://localhost:8880/clients';
  private baseUrl1 = 'http://localhost:8888/voitures'; 



  getAllVoitures(): Observable<any> {
    return this.http.get(`http://localhost:8888/voituress`);
  }

  getVoitureById(id: number): Observable<any> {
    return this.http.get(`http://localhost:8888/voitures/${id}`);
  }

  createVoiture(clientId: number, voiture: any): Observable<any> {
    return this.http.post(`http://localhost:8888/voitures/${clientId}`, voiture);
  }

  updateVoiture(id: number, voiture: any): Observable<any> {
    return this.http.put(`http://localhost:8888/voitures/${id}`, voiture);
  }

  deleteVoiture(id: number): Observable<any> {
    return this.http.delete(`http://localhost:8888/voitures/${id}`);
  }

  getAllClients(): Observable<any> {
    return this.http.get(`http://localhost:8888/clients`);
  }
}
