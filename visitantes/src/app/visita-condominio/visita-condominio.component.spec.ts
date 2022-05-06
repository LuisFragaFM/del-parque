import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisitaCondominioComponent } from './visita-condominio.component';

describe('VisitaCondominioComponent', () => {
  let component: VisitaCondominioComponent;
  let fixture: ComponentFixture<VisitaCondominioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VisitaCondominioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VisitaCondominioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
