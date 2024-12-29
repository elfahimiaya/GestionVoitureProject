import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Voiture } from '../models/voiture';
import { Client } from '../models/client';

@Component({
  selector: 'app-voiture-dialog',
  templateUrl: './voiture-dialog.component.html',
  styleUrls: ['./voiture-dialog.component.css']
})
export class VoitureDialogComponent {
  newVoiture: Voiture = new Voiture();
  clients: Client[] = [];
  selectedClientId: number = 0;
  isEditMode: boolean = false;

  constructor(
    private dialogRef: MatDialogRef<VoitureDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    if (data?.voiture) {
      this.isEditMode = true;
      this.newVoiture = { ...data.voiture };
      this.selectedClientId = data.voiture.id_client;
    }
    this.clients = data?.clients || [];
  }

  ajouterVoiture(): void {
    if (!this.newVoiture.marque || !this.newVoiture.model || !this.newVoiture.matricule || this.selectedClientId === 0) {
      alert("Tous les champs sont obligatoires !");
      return;
    }

    if (this.isEditMode) {
      this.data.updateVoiture(this.newVoiture);
    } else {
      this.data.createVoiture(this.selectedClientId, this.newVoiture);
    }
    this.dialogRef.close();
  }

  onClose(): void {
    this.dialogRef.close();
  }
}
