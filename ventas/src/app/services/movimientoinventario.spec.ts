import { TestBed } from '@angular/core/testing';

import { MovimientoInventarioService } from './movimientoinventario';

describe('MovimientoInventarioService', () => {
  let service: MovimientoInventarioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MovimientoInventarioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
