import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AltasModificacionesComponent } from './altas-modificaciones.component';

describe('AltasModificacionesComponent', () => {
  let component: AltasModificacionesComponent;
  let fixture: ComponentFixture<AltasModificacionesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AltasModificacionesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AltasModificacionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
