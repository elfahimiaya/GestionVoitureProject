import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { VoitureDialogComponent } from '../../voiture-dialog/voiture-dialog.component';
import { ApiService } from '../../service/api.service';
import { Voiture } from '../../models/voiture';
import { Client } from '../../models/client';

@Component({
  selector: 'app-voiture',
  templateUrl: './voiture.component.html',
  styleUrls: ['./voiture.component.css']
})
export class VoitureComponent {
  voitures: Voiture[] = [];
  clients: Client[] = [];
  successMessage: any;
  errorMessage: any;

  constructor(
    private voitureService: ApiService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.getAllVoitures();
    this.getAllClients();
  }

  getAllVoitures(): void {
    this.voitureService.getAllVoitures().subscribe(data => {
      this.voitures = data;
    });
  }

  getAllClients(): void {
    this.voitureService.getAllClients().subscribe(data => {
      this.clients = data;
    });
  }

  openDialog(voiture: Voiture | null): void {
    const dialogRef = this.dialog.open(VoitureDialogComponent, {
      width: '500px',
      data: {
        voiture: voiture,
        clients: this.clients,
        createVoiture: (clientId: number, voiture: Voiture) => {
          this.voitureService.createVoiture(clientId, voiture).subscribe(() => {
            this.getAllVoitures();
          });
        },
        updateVoiture: (voiture: Voiture) => {
          this.voitureService.updateVoiture(voiture.id, voiture).subscribe(() => {
            this.getAllVoitures();
          });
        }
      }
    });
  }

  // Open dialog for "Ajouter"
  openAddDialog(): void {
    this.openDialog(null);
  }

  // Open dialog for "Modifier"
  openEditDialog(voiture: Voiture): void {
    this.openDialog(voiture);
  }

  // Hide the row (mark voiture as deleted)
  deleteVoiture(voiture: Voiture): void {
    voiture.isDeleted = true;
    this.successMessage = `La voiture ${voiture.marque} ${voiture.model} a été supprimée avec succès.`;
    this.errorMessage = null; // You can add additional logic here to send a request to the backend to delete the voiture.
  }
}
