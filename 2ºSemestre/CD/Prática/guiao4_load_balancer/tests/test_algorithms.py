import pytest
import time

from load_balancer import N2One, RoundRobin, LeastConnections, LeastResponseTime

SERVERS = [("localhost", 5000), ("localhost", 5001), ("localhost", 5002), ("localhost", 5003)]

def release_socket():
    while True:
        for peername in [('localhost', 5000), ('localhost', 5003), ('localhost', 5002)]:
            yield peername

def test_n2one():
    n2one = N2One(SERVERS)

    res = []
    for _ in range(8):
        res.append(n2one.select_server())
    
    assert res == [("localhost", 5000), ("localhost", 5000), ("localhost", 5000), ("localhost", 5000), ("localhost", 5000), ("localhost", 5000), ("localhost", 5000), ("localhost", 5000)]

def test_rr():
    rr = RoundRobin(SERVERS)

    res = []
    for _ in range(8):
        res.append(rr.select_server())
    
    assert res == [("localhost", 5000), ("localhost", 5001), ("localhost", 5002), ("localhost", 5003), ("localhost", 5000), ("localhost", 5001), ("localhost", 5002), ("localhost", 5003)]

def test_rr_update():
    rr = RoundRobin(SERVERS)

    res = []
    finished_peers = release_socket()
    for r in range(8):
        res.append(rr.select_server())
        if r > 3:
            rr.update(next(finished_peers))

    assert res == [("localhost", 5000), ("localhost", 5001), ("localhost", 5002), ("localhost", 5003), ("localhost", 5000), ("localhost", 5001), ("localhost", 5002), ("localhost", 5003)]


def test_least_connections():
    lc = LeastConnections(SERVERS)

    res = []
    for _ in range(8):
        res.append(lc.select_server())
    
    assert res == [("localhost", 5000), ("localhost", 5001), ("localhost", 5002), ("localhost", 5003), ("localhost", 5000), ("localhost", 5001), ("localhost", 5002), ("localhost", 5003)]


def test_least_connections_update():
    lc = LeastConnections(SERVERS)

    res = []
    finished_peers = release_socket()
    for r in range(8):
        res.append(lc.select_server())
        if r > 3:
            lc.update(next(finished_peers))
    
    assert res == [("localhost", 5000), ("localhost", 5001), ("localhost", 5002), ("localhost", 5003), ("localhost", 5000), ("localhost", 5000), ("localhost", 5003), ("localhost", 5002)]


def test_least_response_time():
    lrt = LeastResponseTime(SERVERS)

    res = []
    for _ in range(8):
        res.append(lrt.select_server())
    
    assert res == [("localhost", 5000), ("localhost", 5001), ("localhost", 5002), ("localhost", 5003), ("localhost", 5000), ("localhost", 5001), ("localhost", 5002), ("localhost", 5003)]


def test_least_response_time_update():
    lrt = LeastResponseTime(SERVERS)

    res = []
    finished_peers = release_socket()
    for r in range(8):
        res.append(lrt.select_server())
        if r > 3:
            lrt.update(next(finished_peers))
        time.sleep(0.1)
    
    assert res == [("localhost", 5000), ("localhost", 5001), ("localhost", 5002), ("localhost", 5003), ("localhost", 5000), ("localhost", 5001), ("localhost", 5002), ("localhost", 5001)]