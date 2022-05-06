import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisitaCondominoComponent } from './visita-condomino.component';

describe('VisitaCondominoComponent', () => {
  let component: VisitaCondominoComponent;
  let fixture: ComponentFixture<VisitaCondominoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VisitaCondominoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VisitaCondominoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
