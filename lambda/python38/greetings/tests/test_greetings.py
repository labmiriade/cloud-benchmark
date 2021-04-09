import json

import pytest

from greetings import __version__, main


def test_version():
    assert __version__ == "0.1.0"


@pytest.mark.parametrize(
    ('event',),
    [({}, ), ({'pathParameters': None},), ({'pathParameters': {'yo': 2}},)]
)
def test_noname(event):
    res = main.handler(event, None)
    assert res['statusCode'] == 200
    body = json.loads(res['body'])
    assert body['message'] == 'Hello, world!'
