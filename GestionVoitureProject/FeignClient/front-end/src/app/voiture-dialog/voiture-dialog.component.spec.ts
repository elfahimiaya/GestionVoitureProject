import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VoitureDialogComponent } from './voiture-dialog.component';

describe('VoitureDialogComponent', () => {
  let component: VoitureDialogComponent;
  let fixture: ComponentFixture<VoitureDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VoitureDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VoitureDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
