import json


def handler(event, _):
    n = int(event['pathParameters']['n'])

    aux = []
    for i in range(1, n+1):
        if n % i == 0:
            aux.append(i)

    return {
        'statusCode': 200,
        'body': json.dumps(aux)
    }
