import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../service/api.service';
import { Client } from '../../models/client';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {
  clients: Client[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.getAllClients();
  }

  getAllClients(): void {
    this.apiService.getAllClients().subscribe(data => {
      this.clients = data;
    });
  }
}
