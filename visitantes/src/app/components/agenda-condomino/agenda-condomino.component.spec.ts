import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgendaCondominoComponent } from './agenda-condomino.component';

describe('AgendaCondominoComponent', () => {
  let component: AgendaCondominoComponent;
  let fixture: ComponentFixture<AgendaCondominoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgendaCondominoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AgendaCondominoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
